package cn.wellcare.service.transaction.payment.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.wellcare.core.bean.DomainUrlUtil;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.HttpClientUtil;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.common.AccountPaymentResult;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.support.EnumerateParameter;

/**
 * 账户支付
 */

@Service("accountPayService")
public class AccountPayService extends AccountPayment implements PaymentApi {

    @Resource
    private IOrderService orderService;
    @Override
    protected String getaccountPayOrderType() {
        return PaymentType.ACCOUNT_PAY.getPaymentCode();
    }
    @Override
    protected String getaccountPayPaymentName() {
        return PaymentType.ACCOUNT_PAY.getPaymentName();
    }

    @PaymentLog(PayLogHandler.CREATE)
    @Override
    public RpcResult<PaymentResult> doPay(Map<String, Object> param) {
        RpcResult<PaymentResult> sr = new RpcResult<>();
        try {
            // 1.支付前操作
            PayOrder po = payBefore(param);
            if (CommonUtils.isNull(po)) {
                throw new BusinessException("创建订单失败");
            }

            StringBuffer url = new StringBuffer();
            url.append(DomainUrlUtil.PSC_ACCOUNT_URL);
            url.append("/unifyPay/doAccpay");
            BigDecimal amount = new BigDecimal(String.valueOf(param.get(BaseParam.PAY_AMOUNT)));//获取账户支付金额
			// 调用账户中心进行相关操作
			String data = HttpClientUtil.doPost(url.toString(), param);
			Gson gson = new GsonBuilder().create();
			RpcResult<AccountPaymentResult> paymentResultServiceResult = gson.fromJson(data,
					new TypeToken<RpcResult<AccountPaymentResult>>() {
					}.getType());
			AccountPaymentResult paymentResult = paymentResultServiceResult.getData();

			RpcResult<PayOrder> payOrder = orderService.getOrderByOuterSn(po.getOuterOrderSn());// 查询订单信息
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String date = localDateTime.format(dateTimeFormatter);

            PayOrder order = payOrder.getData();
            order.setOrderState(Integer.valueOf(EnumerateParameter.SIX));
            order.setPaymentStatus(Integer.valueOf(EnumerateParameter.ONE));
            order.setTradeSn("zh" + date);
            order.setMoneyPaidBalance(new BigDecimal(paymentResult.getAmtAcc()));
            orderService.updateOrder(order); //更新订单状态
			sr.setData(new PaymentResult(amount.toString(), po.getId()));
        } catch (Exception e) {
            sr.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode())){
                    sr.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());}
                else{
                    sr.setMsgInfo(e.getMessage());}
                sr.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
            } else {
                if (e instanceof UnauthorizedException) {
                    sr.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
                } else {
                    e.printStackTrace();
                    sr.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
                }
            }
            throw e;
        }
        return sr;

    }

    @Override
    public RpcResult<PaymentResult> payQuery(Map<String, Object> param) {
        return null;
    }

    @Override
    public RpcResult<PaymentResult> payRefund(Map<String, Object> param) {
        return null;
    }

    @Override
    public RpcResult<PaymentResult> payRefundQuery(Map<String, Object> param) {
        return null;
    }
}
