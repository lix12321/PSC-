package cn.wellcare.handler.transaction.payment.mispos;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.order.PayPosOrder;
import cn.wellcare.model.modules.order.PayPosOrderModel;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.mispos.MisPosResult;

/**
 * 微信扫码支付
 * 
 * @author zhaihl
 *
 */
@Service
public class MisPosPaymentHandler {
	protected Logger log = Logger.getLogger(this.getClass());
	@Resource
	private IOrderService orderService;
	@Resource
	private PayPosOrderModel payPosOrderModel;

	/**
	 * MISPOS支付，只创建订单
	 */
	public PaymentResult doPay(Map<String, Object> params) {
		MisPosResult mr = new MisPosResult();
		try {
			// 1.订单处理
			PayOrder order = (PayOrder) params.get(Constants.ORDERS_INFO);

			// 2.创建pos子订单
			PayPosOrder posorder = new PayPosOrder();
			posorder.setOrderId(order.getId());
			posorder.setBankCode((String) params.get("bankCode"));
			posorder.setCardNo((String) params.get("cardNo"));
			posorder.setAmount(new BigDecimal((String) params.get(BaseParam.PAY_AMOUNT)));
			posorder.setTrace((String) params.get("trace"));
			posorder.setRefer((String) params.get("refer"));
			posorder.setSzOrderTrace((String) params.get("szOrderTrace"));
			posorder.setTerno((String) params.get("terno"));
			posorder.setOldTerno((String) params.get("oldTerno"));
			posorder.setDtPayMode((String) params.get("dtPayMode"));
			// posorder.setSettleStatus((String) params.get("settleStatus"));

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(Long.valueOf((String) params.get(BaseParam.CLIENT_TRADE_TIME)));
			posorder.setDate(cal.getTime());
			posorder.setUpdateTime(new Date());
			posorder.setRespCode((String) params.get("respCode"));
			posorder.setBatch((String) params.get("batch"));
			posorder.setAuth((String) params.get("auth"));
			posorder.setExpr((String) params.get("expr"));
			posorder.setUserno((String) params.get("userno"));
			this.payPosOrderModel.savePayPosOrder(posorder);

			if (order != null) {
				mr.setOrderId(order.getId());
				mr.setPayInfo(order.getPaySn());
				mr.setTotalFee(order.getMoneyOrder().toString());
			}
		} catch (Exception e) {
			mr.setSuccess(false);
			throw e;
		}
		return mr;
	}

}
