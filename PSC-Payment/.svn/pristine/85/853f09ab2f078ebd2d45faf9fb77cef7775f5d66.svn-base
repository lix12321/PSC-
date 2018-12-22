package cn.wellcare.service.transaction.notify.alipay;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alipay.api.internal.util.AlipaySignature;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.NotifyType;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.annotations.Notify;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.model.base.OrderModel;
import cn.wellcare.payment.notify.PaymentNotifyRpc;
import cn.wellcare.pojo.alipay.AlipayConfig;

@Service("alipayNotifyService")
public class AlipayNotifyService implements PaymentNotifyRpc {
	@Resource
	private OrderModel orderModel;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	@PaymentLog(PayLogHandler.UPDATE)
	@Notify(NotifyType.AFTER_NOTIFY)
	public boolean doNotify(Map<String, Object> param) {
		boolean success = false;
		try {
			// this.alipayNotifyModel.payNotify(param);

			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			for (Iterator iter = param.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = { param.get(name).toString() };
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(String.valueOf(param.get("out_trade_no")).getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(String.valueOf(param.get("trade_no")).getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(String.valueOf(param.get("trade_status")).getBytes("ISO-8859-1"), "UTF-8");

			// 交易金额
			String total_fee = new String(String.valueOf(param.get("total_fee")).getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 计算得出通知验证结果
			// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
			// publicKey, String charset, String sign_type)
			/*if (AlipayNotify.verify(params)) {// 验证成功*/
			if (AlipaySignature.rsaCheckV1(params,AlipayConfig.publicKey, AlipayConfig.input_charset,
					"RSA2")) {// 验证成功

				//////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED")) {
					throw new BusinessException("支付宝验签失败");
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 1.查询订单
					PayOrder order = this.orderModel.queryOrderBySn(out_trade_no);
					// 交易流水号
					order.setTradeSn(trade_no);
					// 状态已完成
					order.setOrderState(PayOrder.ORDER_STATE_FINISH);
					// 已付款
					order.setPaymentStatus(PayOrder.ORDER_PAY_STATUS_PAEID);
					order.setFinishTime(new Date());
					order.setUpdateTime(new Date());
					// 2.更新订单
					boolean up = this.orderModel.updateOrder(order);
					if (!up) {
						this.log.info("更改订单状态失败,支付失败");
						throw new BusinessException("更改订单状态失败,支付失败");
					}
					// 返回订单
					param.put(Constants.ORDERS_INFO, order);
				}

			} else {// 验证失败
				this.log.debug("验签失败");
				// 通知日志
			}

			success = true;
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

}
