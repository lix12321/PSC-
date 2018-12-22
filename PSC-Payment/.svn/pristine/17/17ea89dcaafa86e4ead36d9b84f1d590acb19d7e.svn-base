package cn.wellcare.service.transaction.query.integration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.system.SysCodeMaster;
import cn.wellcare.model.modules.system.SysCodeMasterModel;
import cn.wellcare.model.payment.account.PscPiAccModel;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.account.PscPiAcc;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.integrationpay.IntegrationPayConfig;
import cn.wellcare.service.transaction.payment.integrationpay.bean.AggregatePayOrderOutVo;
import cn.wellcare.service.transaction.payment.integrationpay.bean.AggregatePayOrderResult;
import cn.wellcare.support.EnumerateParameter;
import cn.wellcare.support.HttpClientUtil;
import cn.wellcare.support.MD5Util;
import cn.wellcare.support.XmlUtil;
@Service
public class IntegrationOrderService {
    @Resource
    private PscPiAccModel piAccModel;
    @Resource
    private SysCodeMasterModel sysCodeMasterModel;

    private Logger log = Logger.getLogger(this.getClass());

    public OrderResult getOrderBySn(Map<String, Object> param, PayOrder payOrder) {
        String userId = String.valueOf(param.get(BaseParam.USER_ID));
        String orgId = String.valueOf(param.get(BaseParam.ORG_ID));
        String orderState = String.valueOf(payOrder.getOrderState());
        String orderDate = String.valueOf(payOrder.getFinishTime());
        String BEGORDERID="";
        String ENDORDERID="";
        String SEL_TYPE=EnumerateParameter.THREE;//1页面形式 2文件返回形式 (提供TXT和XML格式文件的下载) 3 XML页面形式
        String OPERATOR=""; //操作员 可为空
        //txcode=410408
        //目前只查询支付流水，待退款做完后在完善
        // TODO 直接查询订单：状态3-->1 6-->0
        String TYPE=EnumerateParameter.ZERO;//0  支付流水，1 退款流水
        // 订单已退款-->已结算 ！其它状态都是未结算（如果需要线下提款，需要添加相应功能）
        String KIND=EnumerateParameter.ZERO;//0 未结算流水 1 已结算流水
        if (orderState.equalsIgnoreCase(EnumerateParameter.THREE)) {
            TYPE = EnumerateParameter.ONE;
            KIND = EnumerateParameter.ONE;
        }
        // TODO 固定为全部
        String STATUS=EnumerateParameter.THREE;//0失败 1成功 2不确定 3全部
        // 没有范围查询，页码固定1
        String PAGE = EnumerateParameter.ONE;//页码
        String CHANNEL = ""; //预留字段

        // 组装验签字段
        StringBuffer tmp = new StringBuffer(); // 验签字段
        tmp.append("MERCHANTID=");
        tmp.append(IntegrationPayConfig.MERCHANTID);
        tmp.append("&BRANCHID=");
        tmp.append(IntegrationPayConfig.BRANCHID);
        tmp.append("&POSID=");
        tmp.append(IntegrationPayConfig.POSID);
        tmp.append("&ORDERDATE=");//订单号查询交易流水，订单日期不起作用
        tmp.append("");
        tmp.append("&BEGORDERTIME=");
        tmp.append(IntegrationPayConfig.BEGORDERTIME);
        tmp.append("&ENDORDERTIME=");
        tmp.append(IntegrationPayConfig.ENDORDERTIME);
        tmp.append("&ORDERID=");
        tmp.append(payOrder.getPaySn());
        tmp.append("&QUPWD=&TXCODE=");
        tmp.append(IntegrationPayConfig.TXCODES);
        tmp.append("&TYPE=");
        tmp.append(TYPE);
        tmp.append("&KIND=");
        tmp.append(KIND);
        tmp.append("&STATUS=");
        tmp.append(STATUS);
        tmp.append("&SEL_TYPE=");
        tmp.append(SEL_TYPE);
        tmp.append("&PAGE=");
        tmp.append(PAGE);
        tmp.append("&OPERATOR=");
        tmp.append(OPERATOR);
        tmp.append("&CHANNEL=");
        tmp.append(CHANNEL);

        //组装查询字段
        Map map = new HashMap();
        map.put("MERCHANTID",IntegrationPayConfig.MERCHANTID);

        map.put("BRANCHID",IntegrationPayConfig.BRANCHID);

        map.put("POSID",IntegrationPayConfig.POSID);

        map.put("ORDERDATE","");

        map.put("BEGORDERTIME",IntegrationPayConfig.BEGORDERTIME);

        map.put("ENDORDERTIME",IntegrationPayConfig.ENDORDERTIME);

        map.put("BEGORDERID",BEGORDERID);

        map.put("ENDORDERID",ENDORDERID);

        map.put("QUPWD",IntegrationPayConfig.PASSWORD);

        map.put("TXCODE",IntegrationPayConfig.TXCODES);

        map.put("TYPE",TYPE);

        map.put("KIND",KIND);

        map.put("STATUS",STATUS);

        map.put("ORDERID",payOrder.getPaySn());

        map.put("PAGE",PAGE);

        map.put("CHANNEL",CHANNEL);

        map.put("SEL_TYPE",SEL_TYPE);

        map.put("OPERATOR",OPERATOR);



        map.put("MAC",MD5Util.md5Str(tmp.toString()));

        String ret = HttpClientUtil.httpPost(IntegrationPayConfig.BANKURL, map).trim();//发送查询请求
        AggregatePayOrderOutVo aggregatePayOrderOutVo = (AggregatePayOrderOutVo) XmlUtil.XmlToBean(ret,AggregatePayOrderOutVo.class);
        if (!aggregatePayOrderOutVo.getReturnCode().equals("000000")){
            throw new BusinessException(String.format("聚合支付失败码: %s, 返回信息: %s",
                    aggregatePayOrderOutVo.getReturnCode(), aggregatePayOrderOutVo.getReturnMsg()));
        }
        String returnCode = Constants.RESONSE_SUCCESS_CODE;
        String returnMsg = Constants.RESONSE_SUCCESS_MSG;
        AggregatePayOrderResult payOrderVo = aggregatePayOrderOutVo.getAggregatePayOrderResult().get(0);//按订单号查询只有一条数据

        SysCodeMaster codeMaster = sysCodeMasterModel.getSysCodeMasterById("ORDER_STATE",orderState);//查询订单状态对应的数据字典信息

        PscPiAcc  pscPiAcc = piAccModel.getPscPiAccBypkPi(userId);//查询账户信息

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

        return new AggregatePayOrderResult(payOrder.getOuterOrderSn(), codeMaster.getCodeText(), String.valueOf(pscPiAcc.getPkPiacc()), orderDate,
                orderState, sign, returnCode, returnMsg);
    }
}
