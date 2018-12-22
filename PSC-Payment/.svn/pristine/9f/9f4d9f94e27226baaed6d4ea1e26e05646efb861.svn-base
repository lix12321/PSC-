package cn.wellcare.handler.transaction.payment.alipay;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.TradeStatus;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.alipay.AlipayConfig;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.service.transaction.payment.alipay.base.AlipayPayment;
import cn.wellcare.support.EnumerateParameter;

/**
 * 支付宝扫码支付
 */
@Service
public class AlipaySaoMaHandler extends AlipayPayment {
	protected Logger log = Logger.getLogger(this.getClass());

	@Resource
	private IOrderService orderService;

	@Override
	protected String getAlipayOrderType() {
		return PaymentType.ALIPAY_SAOMA.getPaymentCode();
	}

	@Override
	protected String getAlipayPaymentName() {
		return PaymentType.ALIPAY_SAOMA.getPaymentName();
	}

	public PaymentResult doPay(Map<String, Object> param) {
		PaymentResult pr = new PaymentResult();
		try {
			// TODO
			Configs.init("zfbinfo.properties");
			PayOrder po = (PayOrder) param.get(Constants.ORDERS_INFO);
			Assert.notNull(po);

			String orderPaySn = po.getPaySn();

			String totalFee = (String) param.get(BaseParam.PAY_AMOUNT);

			// 创建条码支付请求builder，设置请求参数
			AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder();
			builder.setAuthCode((String) param.get(BaseParam.AUTH_CODE));// 支付授权码
			builder.setOutTradeNo(orderPaySn);// 订单号
			builder.setSubject(AlipayConfig.ALIPAY_ALL_SUBJECT); // 订单标题
			builder.setTotalAmount(totalFee);
			builder.setStoreId((String) param.get(BaseParam.DEVICE_INFO)); // 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，可填具体设备号
			builder.setUndiscountableAmount("0");
			builder.setBody(Constants.ALIPAY_ORDER_SUBJECT); // 订单描述，可以对交易或商品进行一个详细地描述，
			builder.setTimeoutExpress("5m"); // 支付超时，线下扫码交易定义为5分钟

			AlipayTradeServiceImpl tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
			AlipayF2FPayResult result = tradeService.tradePay(builder);
			String msgString = "";
			switch (result.getTradeStatus()) {
			case SUCCESS:
				log.info("支付宝支付成功: )");
				break;

			case FAILED:
				msgString = "支付宝支付失败!!!";
				log.error(msgString);
				break;

			case UNKNOWN:
				msgString = "系统异常，订单状态未知!!!";
				log.error(msgString);
				break;

			default:
				msgString = "不支持的交易状态，交易返回异常!!!";
				log.error(msgString);
				break;
			}

			if (!result.getTradeStatus().equals(TradeStatus.SUCCESS)) {
				throw new BusinessException(String.format("交易号: %s, 支付错误状态: %s, 支付错误明细: %s", po.getOuterOrderSn(),
						result.getTradeStatus(), msgString));
			}
			// 更新订单状态
			po.setOrderState(Integer.valueOf(EnumerateParameter.SIX));
			po.setTradeSn(result.getResponse().getTradeNo());
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
