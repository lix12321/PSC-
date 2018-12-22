package cn.wellcare.service.transaction.notify.integrationpay;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.NotifyType;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.annotations.Notify;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.utils.CCBUtil;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.model.base.OrderModel;
import cn.wellcare.payment.notify.PaymentNotifyRpc;
import cn.wellcare.pojo.integrationpay.IntegrationPayConfig;

@Service("integrationpayNotifyService")
public class IntegrationpayNotifyService implements PaymentNotifyRpc {
	@Resource
	private OrderModel orderModel;
	private Logger log = Logger.getLogger(this.getClass());

	@PaymentLog(PayLogHandler.UPDATE)
	@Notify(NotifyType.AFTER_NOTIFY)
	@Override
	public boolean doNotify(Map<String, Object> param) {
		boolean success = true;
		try {
			// this.integrationPayNotifyModel.payNotify(param);
			Map<String, String> params = new HashMap<String, String>(16);
			for (Iterator<String> iter = param.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String[] values = { param.get(name).toString() };
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}

			String sign = String.valueOf(param.get("SIGN"));
			String outTradeNo = String.valueOf(param.get("ORDERID"));
			String serialNo = outTradeNo.substring(15);// 去掉前15位
			String totalFee = String.valueOf(param.get("PAYMENT")); // 获取金额
			StringBuffer src = new StringBuffer();
			src.append("POSID=");
			src.append(String.valueOf(param.get("POSID")));
			src.append("&BRANCHID=");
			src.append(String.valueOf(param.get("BRANCHID")));
			src.append("&ORDERID=");
			src.append(outTradeNo);
			src.append("&PAYMENT=");
			src.append(totalFee);
			src.append("&CURCODE=");
			src.append(String.valueOf(param.get("CURCODE")));
			src.append("&REMARK1=");
			src.append(String.valueOf(param.get("REMARK1")));
			src.append("&REMARK2=");
			src.append(String.valueOf(param.get("REMARK2")));
			src.append("&ACC_TYPE=");
			src.append(String.valueOf(param.get("ACC_TYPE")));
			src.append("&SUCCESS=");
			src.append(String.valueOf(param.get("SUCCESS")));
			src.append("&TYPE=");
			src.append(String.valueOf(param.get("TYPE")));
			src.append("&REFERER=");
			if (!"null".equals(String.valueOf(param.get("REFERER")))) {
				src.append(String.valueOf(param.get("REFERER")));
			}
			src.append("&CLIENTIP=");
			src.append(String.valueOf(param.get("CLIENTIP")));
			String a = String.valueOf(param.get("ACCDATE"));
			if (!"null".equals(String.valueOf(param.get("ACCDATE")))) {
				src.append("&ACCDATE=");
				src.append(String.valueOf(param.get("ACCDATE")));
			}

			if (!"null".equals(String.valueOf(param.get("USRMSG")))) {
				src.append("&USRMSG=");
				// 可能需要解密
				src.append(String.valueOf(param.get("USRMSG")));
			}
			if (!"null".equals(String.valueOf(param.get("USRINFO")))) {
				src.append("&USRINFO=");
				src.append(String.valueOf(param.get("USRINFO")));
			}

			if (!"null".equals(String.valueOf(param.get("PAYTYPE")))) {
				src.append("&PAYTYPE=");
				src.append(String.valueOf(param.get("PAYTYPE")));
			}

			String sb = src.toString();
			CCBUtil cUtil = new CCBUtil();
			// 获取公钥
			String pkey = IntegrationPayConfig.PUBLICKEY;

			cUtil.setPublicKey(pkey);
			PayOrder order = null;
			// 验证
			if (cUtil.verifySigature(sign, sb)) {
				// 验签成功

				// 1.查询订单
				order = this.orderModel.queryOrderBySn(outTradeNo);
				// 交易流水号 聚合支付暂时没返回
				// order.setTradeSn(trade_no);
				// 状态已完成
				order.setOrderState(PayOrder.ORDER_STATE_FINISH);
				// 已付款
				order.setPaymentStatus(PayOrder.ORDER_PAY_STATUS_PAEID);
				order.setUpdateTime(new Date());
				// 2.更新订单
				boolean up = this.orderModel.updateOrder(order);
				// 返回订单
				param.put(Constants.ORDERS_INFO, order);
				if (!up) {
					this.log.info("更改订单状态失败,支付失败");
					throw new BusinessException("更改订单状态失败,支付失败");
				}
				// TODO order

			} else { // 签名验证失败
				throw new BusinessException("聚合支付验签失败");
			}
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}
}
