package cn.wellcare.handler.transaction.payment.wechat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.service.settins.WXPayByCard;
import cn.wellcare.service.settins.WechatSettings;
import cn.wellcare.service.transaction.payment.wechat.base.WechatPayment;
import cn.wellcare.support.EnumerateParameter;

@Service
public class WechatSaoMaPayHandler extends WechatPayment {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private IOrderService orderService;
	@Resource
	private WXPayByCard wXPayByCard;
	@Resource
	private WechatSettings wechatSettings;

	@Override
	protected String getWechatOrderType() {
		return PaymentType.WECHAT_SAOMA.getPaymentCode();
	}

	@Override
	protected String getWechatPaymentName() {
		return PaymentType.WECHAT_SAOMA.getPaymentName();
	}

	public PaymentResult doPay(Map<String, Object> param) {
		PaymentResult pr = new PaymentResult();
		try {
			// 1.订单处理
			PayOrder po = (PayOrder) param.get(Constants.ORDERS_INFO);
			Assert.notNull(po);

			String orderPaySn = po.getPaySn();

			String totalFee = (String) param.get(BaseParam.PAY_AMOUNT);

			// 支付的总金额（分）
			BigDecimal needsPay = new BigDecimal(totalFee);
			needsPay = needsPay.multiply(new BigDecimal(100));
			String txnAmt = needsPay.toString().split("\\.")[0]; // 付款金额，单位为分，不能有小数点，去掉

			Map<String, String> dataMap = new HashMap<>();
			dataMap.put("device_info", (String) param.get(BaseParam.DEVICE_INFO));// 设备终端号
			dataMap.put("body", Constants.WEIXIN_ORDER_NAME);
			dataMap.put("out_trade_no", orderPaySn);
			// dataMap.put("fee_type", ); //CNY 默认值可不填
			dataMap.put("total_fee", txnAmt);
			// dataMap.put("spbill_create_ip", (String) param.get(BaseParam.CLIENT_IP));
			dataMap.put("spbill_create_ip", "8.8.8.8");// 测试暂时默认IP值
			dataMap.put("auth_code", (String) param.get(BaseParam.AUTH_CODE)); // 扫码支付授权码，设备读取用户微信中的条码或二维码信息

			wechatSettings.init(Integer.valueOf((String) param.get(BaseParam.ORG_ID)));
			Map<String, Object> payReturnMap = wXPayByCard.pay(dataMap, wechatSettings);
			boolean result = (Boolean) (payReturnMap.get("result"));
			String msg = CommonUtils.getString(payReturnMap.get("msg"));
			Map data = (Map) payReturnMap.get("data");
			String err_code_des = CommonUtils.getString(data.get("err_code_des"));
			log.error(String.format("微信支付交易号: %s, 支付信息: %s, 支付结果: %s, 支付错误明细: %s", orderPaySn, msg, result,
					err_code_des));
			if (!result) {
				throw new BusinessException(String.format("支付错误明细: %s", err_code_des));
			}
			// 更新订单状态
			po.setOrderState(Integer.valueOf(EnumerateParameter.SIX));
			po.setTradeSn(CommonUtils.getString(data.get("transaction_id")));
			po.setPaymentStatus(Integer.valueOf(EnumerateParameter.ONE));
			orderService.updateOrder(po); // 更新订单状态

			pr = new PaymentResult(totalFee, po.getId());
		} catch (Exception e) {
			pr.setSuccess(false);
			throw e;
		}
		return pr;
	}

}
