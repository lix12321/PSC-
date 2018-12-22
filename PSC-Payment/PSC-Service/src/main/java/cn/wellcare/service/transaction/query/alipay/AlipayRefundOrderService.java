package cn.wellcare.service.transaction.query.alipay;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.demo.trade.config.Configs;

import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.refund.PayRefund;
import cn.wellcare.entity.system.SysCodeMaster;
import cn.wellcare.model.modules.refund.PayRefundModel;
import cn.wellcare.model.modules.system.SysCodeMasterModel;
import cn.wellcare.model.payment.account.PscPiAccModel;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.account.PscPiAcc;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.service.transaction.payment.alipay.base.AlipayTradeResult;
@Service
public class AlipayRefundOrderService {
    @Resource
    private PscPiAccModel piAccModel;
    @Resource
    private PayRefundModel payRefundModel;
    @Resource
    private SysCodeMasterModel sysCodeMasterModel;
    protected Logger log = Logger.getLogger(this.getClass());
    public OrderResult getOrderBySn(Map<String, Object> param, PayOrder payOrder) {
        try {
            String userId = String.valueOf(param.get(BaseParam.USER_ID));
            String orgId = String.valueOf(param.get(BaseParam.ORG_ID));
            String orderState = String.valueOf(payOrder.getOrderState());
            String orderDate = String.valueOf(payOrder.getFinishTime());

            PayRefund payRefund = payRefundModel.queryPayRefundById(payOrder.getId());
            Configs.init("zfbinfo.properties");
            AlipayTradeFastpayRefundQueryRequest refundQueryRequest = new AlipayTradeFastpayRefundQueryRequest();
            AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
            model.setOutTradeNo(payOrder.getPaySn());
            model.setOutRequestNo(payRefund.getApplyNo());//退款请求号，退款时传入的请求号
            refundQueryRequest.setBizModel(model);
            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",Configs.getAppid(),Configs.getPrivateKey(),"json","GBK",Configs.getAlipayPublicKey(),"RSA2");
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(refundQueryRequest);
            if (!response.isSuccess()||!response.getCode().equals("10000")){
                System.out.println("调用失败");
                throw new BusinessException(String.format("支付宝失败码: %s, 返回信息: %s",
                        response.getCode(),response.getSubMsg()));
            }
            if (StringUtils.isEmpty(response.getOutTradeNo())) {
                throw new BusinessException("订单信息未获取到");
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
