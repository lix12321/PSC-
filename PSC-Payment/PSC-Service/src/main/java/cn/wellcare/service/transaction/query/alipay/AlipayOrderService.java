package cn.wellcare.service.transaction.query.alipay;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;

import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.system.SysCodeMaster;
import cn.wellcare.model.modules.system.SysCodeMasterModel;
import cn.wellcare.model.payment.account.PscPiAccModel;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.account.PscPiAcc;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.service.transaction.payment.alipay.base.AlipayTradeResult;
@Service
public class AlipayOrderService {
    @Resource
    private PscPiAccModel piAccModel;
    @Resource
    private SysCodeMasterModel sysCodeMasterModel;
    protected Logger log = Logger.getLogger(this.getClass());
    public OrderResult getOrderBySn(Map<String, Object> param, PayOrder payOrder) {
        try {
            String userId = String.valueOf(param.get(BaseParam.USER_ID));
            String orgId = String.valueOf(param.get(BaseParam.ORG_ID));
            String orderState = String.valueOf(payOrder.getOrderState());
            String orderDate = String.valueOf(payOrder.getFinishTime());
            Configs.init("zfbinfo.properties");
            AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder();
            builder.setOutTradeNo(payOrder.getPaySn());
            AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

            AlipayF2FQueryResult queryResult = tradeService.queryTradeResult(builder);
            AlipayTradeQueryResponse z = null;
            String status = "";
        if(queryResult.isTradeSuccess()){

            z =queryResult.getResponse();
            if (!z.getCode().equals("10000")){
                throw new BusinessException(String.format("支付宝失败码: %s, 返回信息: %s",
                        z.getCode(),z.getSubMsg()));
            }
            switch (z.getTradeStatus()) {
                case "WAIT_BUYER_PAY":
                    log.info("交易创建，等待买家付款");
                    break;
                case "TRADE_CLOSED":
                    status = "未付款交易超时关闭，或支付完成后全额退款";
                    break;
                case "TRADE_SUCCESS":
                    status = "交易支付成功";
                    break;
                default:
                    status = "交易结束，不可退款";
                    break;
            }

        } else {
            System.out.println("调用失败");
            //待完善
            z =queryResult.getResponse();
            if (!z.getCode().equals("10000")){
                throw new BusinessException(String.format("支付宝失败码: %s, 返回信息: %s",
                        z.getCode(),z.getSubMsg()));
            }
        }

            String returnCode = Constants.RESONSE_SUCCESS_CODE;
            String returnMsg = Constants.RESONSE_SUCCESS_MSG;

            SysCodeMaster codeMaster = sysCodeMasterModel.getSysCodeMasterById("ORDER_STATE",orderState);//查询订单状态对应的数据字典信息

            PscPiAcc pscPiAcc = piAccModel.getPscPiAccBypkPi(userId);//查询账户信息

            //对返回的参数进行加密
            param.clear();
            param.put("orderId",payOrder.getOuterOrderSn());
            param.put("status",codeMaster.getCodeText());
            param.put("accId", String.valueOf(pscPiAcc.getPkPiacc()));
            param.put("orderDate", orderDate);
            param.put("statusCode",orderState);
            param.put("returnCode",returnCode);
            param.put("returnMsg",returnMsg);
            Map<String, Object> sorted = CommonUtils.sort(param);
            this.log.debug("客户端排序后参数集：" + sorted);
            ISysOrganizationService orgService = (ISysOrganizationService) ServiceLocator.getInstance()
                    .getBean("sysOrganizationService");
            String securtkey = orgService.getSysOrganizationById(Integer.valueOf(orgId))
                    .getData().getAuthSecret();
            log.debug("机构密钥：" + securtkey);
            String sign = Md5SignUtil.sginMD5(sorted, securtkey);

        return new AlipayTradeResult(payOrder.getOuterOrderSn(), codeMaster.getCodeText(), String.valueOf(pscPiAcc.getPkPiacc()), orderDate,
                orderState, sign, returnCode, returnMsg);
    }catch (Exception e) {
            if (e instanceof BusinessException) {
                throw new BusinessException(e.getMessage());
            } else {
                e.printStackTrace();
                throw new BusinessException(ErrorEnum.SERVER_EXCEPTION.getErrDesc(),
                        ErrorEnum.SERVER_EXCEPTION.getErrCode());
            }
        }
    }
}
