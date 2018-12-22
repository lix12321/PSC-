package cn.wellcare.service.transaction.notify.wechat;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.NotifyType;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.annotations.Notify;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.payset.PaySettingWechat;
import cn.wellcare.model.base.OrderModel;
import cn.wellcare.model.modules.payset.PaySettingWechatModel;
import cn.wellcare.payment.notify.PaymentNotifyRpc;
import cn.wellcare.service.transaction.payment.wechat.bean.WeChatBuyPost;
import cn.wellcare.service.transaction.payment.wechat.util.RequestHandler;

@Service("wechatNativeNotifyService")
public class WechatNativeNotifyService implements PaymentNotifyRpc {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private OrderModel orderModel;
	@Resource
	private PaySettingWechatModel paySettingWechatModel;

	private static final String VALIDATE_FAIL = "验签失败";

	@Override
	@PaymentLog(PayLogHandler.UPDATE)
	@Notify(NotifyType.AFTER_NOTIFY)
	public boolean doNotify(Map<String, Object> param) {
		boolean success = false;
		try {
			// this.wechatNativeNotifyModel.payNotify(param);
			this.log.debug("微信支付通知开始....");
			this.log.debug("返回数据：" + param);
			WeChatBuyPost postData = null;
			// post 过来的xml
			// 转换微信post过来的xml内容
			XStream xs = new XStream(new DomDriver());
			xs.alias("xml", WeChatBuyPost.class);
			String xmlMsg = new String((byte[]) param.get("reqStream"), "UTF-8");

			postData = (WeChatBuyPost) xs.fromXML(xmlMsg);

			if (!"SUCCESS".equals(postData.getReturn_code()) || CommonUtils.isNull(postData.getOut_trade_no())) {
				throw new BusinessException(VALIDATE_FAIL);
			}

			log.debug("解析后数据：" + postData);
			// BigDecimal totalFee = postData.getTotal_fee();
			// totalFee = totalFee.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);

			// 1.查询订单
			PayOrder order = this.orderModel.queryOrderBySn(postData.getOut_trade_no());
			// 2.安全校验
			validateSign(order.getOrgId(), postData);

			// 交易流水号
			order.setTradeSn(postData.getTransaction_id());
			// 状态已完成
			order.setOrderState(PayOrder.ORDER_STATE_FINISH);
			// 已付款
			order.setPaymentStatus(PayOrder.ORDER_PAY_STATUS_PAEID);
			order.setUpdateTime(new Date());
			order.setFinishTime(new Date());//暂时取系统时间（long类型转date有问题）
			// 3.更改订单状态
			boolean up = this.orderModel.updateOrder(order);
			if (!up) {
				this.log.info("更改订单状态失败,支付失败");
				throw new BusinessException("更改订单状态失败,支付失败");
			}
			// 返回订单
			param.put(Constants.ORDERS_INFO, order);
			log.debug("订单信息：" + order);

			success = true;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * 通知校验<br>
	 * 所有接收到的参数,组成集合做签名
	 * 
	 * @param integer
	 * 
	 * @param postData
	 */
	private void validateSign(Integer orgId, WeChatBuyPost postData) throws Exception {
		log.debug("安全验签开始。。。");
		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("appid", postData.getAppid());
		parameters.put("bank_type", postData.getBank_type());
		parameters.put("cash_fee", postData.getCash_fee());
		parameters.put("fee_type", postData.getFee_type());
		parameters.put("is_subscribe", postData.getIs_subscribe());
		parameters.put("mch_id", postData.getMch_id());
		parameters.put("nonce_str", postData.getNonce_str());
		parameters.put("openid", postData.getOpenid());
		parameters.put("out_trade_no", postData.getOut_trade_no());
		parameters.put("result_code", postData.getResult_code());
		parameters.put("return_code", postData.getResult_code());
		parameters.put("time_end", postData.getTime_end() + "");
		parameters.put("total_fee", postData.getTotal_fee() + "");
		parameters.put("trade_type", postData.getTrade_type());
		parameters.put("transaction_id", postData.getTransaction_id());

		RequestHandler reqHandler = new RequestHandler();

		PaySettingWechat setting = paySettingWechatModel.getByOrg(orgId);
		reqHandler.init(postData.getAppid(), setting.getAppsecret(), setting.getKey());

		String sign = reqHandler.createSign(parameters);
		if (!sign.equals(postData.getSign())) {
			throw new BusinessException(VALIDATE_FAIL);
		} else {
			this.log.debug("签名成功!");
		}
	}

}
