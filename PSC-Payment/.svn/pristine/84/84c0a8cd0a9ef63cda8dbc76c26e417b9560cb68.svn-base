// package cn.wellcare.model.payment.mispos;
//
// import java.math.BigDecimal;
// import java.util.Calendar;
// import java.util.Date;
// import java.util.Map;
//
// import javax.annotation.Resource;
//
// import cn.wellcare.dao.order.PayPosOrderDao;
// import cn.wellcare.entity.order.PayOrder;
// import cn.wellcare.entity.order.PayPosOrder;
// import cn.wellcare.order.IOrderService;
//
// import org.apache.log4j.Logger;
// import org.springframework.stereotype.Component;
//
// import cn.wellcare.core.constant.BaseParam;
// import cn.wellcare.core.constant.PaymentType;
// import cn.wellcare.core.exception.BusinessException;
// import cn.wellcare.core.exception.ErrorEnum;
// import cn.wellcare.unifyPay.UnifyPaymentInfo;
//
/// **
// * 微信扫码支付
// *
// * @author zhaihl
// *
// */
// @Component(value = "misPosPaymentModel")
// public class MisPosPaymentModel {
// protected Logger log = Logger.getLogger(this.getClass());
// @Resource
// private PayPosOrderDao payPosorderDao;
// @Resource
// private IOrderService orderService;
//
// public PayOrder createOrder(Map<String, Object> params) {
// PayOrder order = null;
// try {
// // 1.创建订单
// order = this.orderService.createOrder(params, new UnifyPaymentInfo() {
//
// @Override
// public String getOrderType() {
// return PaymentType.MISPOS.getPaymentCode();
// }
//
// @Override
// public String getPaymentName() {
// return PaymentType.MISPOS.getPaymentName();
// }
//
// public Integer getOrderState() {
// return PayOrder.ORDER_STATE_FINISH;
// }
//
// public Integer getPaymentStatus() {
// return PayOrder.ORDER_PAY_STATUS_PAEID;
// }
// });
//
// // 2.创建pos子订单
// PayPosOrder posorder = new PayPosOrder();
// posorder.setOrderId(order.getId());
// posorder.setBankCode((String) params.get("bankCode"));
// posorder.setCardNo((String) params.get("cardNo"));
// posorder.setAmount(new BigDecimal((String)
// params.get(BaseParam.PAY_AMOUNT)));
// posorder.setTrace((String) params.get("trace"));
// posorder.setRefer((String) params.get("refer"));
// posorder.setSzOrderTrace((String) params.get("szOrderTrace"));
// posorder.setTerno((String) params.get("terno"));
// posorder.setOldTerno((String) params.get("oldTerno"));
// posorder.setDtPayMode((String) params.get("dtPayMode"));
// // posorder.setSettleStatus((String) params.get("settleStatus"));
//
// Calendar cal = Calendar.getInstance();
// cal.setTimeInMillis(Long.valueOf((String)
// params.get(BaseParam.CLIENT_TRADE_TIME)));
// posorder.setDate(cal.getTime());
// posorder.setUpdateTime(new Date());
// posorder.setRespCode((String) params.get("respCode"));
// posorder.setBatch((String) params.get("batch"));
// posorder.setAuth((String) params.get("auth"));
// posorder.setExpr((String) params.get("expr"));
// posorder.setUserno((String) params.get("userno"));
// this.payPosorderDao.save(posorder);
//
// } catch (Exception e) {
// if (e instanceof BusinessException) {
// throw new BusinessException(e.getMessage());
// } else {
// e.printStackTrace();
// throw new BusinessException(ErrorEnum.SERVER_EXCEPTION.getErrDesc(),
// ErrorEnum.SERVER_EXCEPTION.getErrCode());
// }
// }
// return order;
// }
//
// }
