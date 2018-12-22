// package cn.wellcare.model.payment.account;
//
// import java.util.Map;
//
// import javax.annotation.Resource;
//
// import org.apache.log4j.Logger;
// import org.springframework.stereotype.Component;
// import org.springframework.util.Assert;
//
// import cn.wellcare.core.constant.BaseParam;
// import cn.wellcare.core.constant.Constants;
// import cn.wellcare.core.constant.OpType;
// import cn.wellcare.core.constant.PayLogHandler;
// import cn.wellcare.core.constant.PaymentType;
// import cn.wellcare.core.constant.annotations.PaymentLog;
// import cn.wellcare.core.exception.BusinessException;
// import cn.wellcare.core.exception.ErrorEnum;
// import cn.wellcare.core.utils.RandomUtil;
// import cn.wellcare.entity.order.PayOrder;
// import cn.wellcare.model.payment.alipay.AlipayNativePaymentModel;
// import cn.wellcare.model.payment.integrationpay.IntegrationPaymentModel;
// import cn.wellcare.order.IOrderService;
// import cn.wellcare.pojo.common.PaymentResult;
// import cn.wellcare.unifyPay.UnifyPaymentInfo;
//
// @Component
// public class AccRechargeModel {
// @Resource
// private IOrderService orderService;
// @Resource
// private IntegrationPaymentModel integrationPaymentModel;
// @Resource
// private AlipayNativePaymentModel alipayNativePaymentModel;
//
// Logger log = Logger.getLogger(this.getClass());
//
// public PayOrder payBefore(Map<String, Object> params) throws Exception {
// // 1.创建订单
// PayOrder po = this.orderService.createOrder(params, new UnifyPaymentInfo() {
//
// @Override
// public String getPaymentName() {
// return PaymentType.ACCRECHARGE.getPaymentName();
// }
//
// @Override
// public String getOrderType() {
// return PaymentType.ACCRECHARGE.getPaymentCode();
// }
//
// @Override
// public String getOrderSn() {
// return RandomUtil.getOrderSn() + PaymentType.ACCRECHARGE.getPaymentCode();
// }
//
// });
// // 2.返回订单信息
// return po;
// }
//
// @PaymentLog(value = PayLogHandler.CREATE, type = OpType.RECHARGE)
// public PaymentResult doPay(Map<String, Object> param) {
// try {
// Assert.notNull(param.get(BaseParam.RECHARGE_TYPE));
// // 1.支付前操作
// PayOrder po = payBefore(param);
// param.put(Constants.ORDERS_INFO, po);
//
// PaymentResult pr = null;
// // 2.按不同的支付类型调用相应服务
// String payType = PaymentType.getPaymentCodeByNameOrCode((String)
// param.get(BaseParam.RECHARGE_TYPE));
//
// if (PaymentType.WECHAT_NATIVE.getPaymentCode().equals(payType)) {
// // 微信扫码支付
// pr = wechatNativePaymentModel.doPay(param);
// } else if (PaymentType.JUHPAY.getPaymentCode().equals(payType)) {
// // 聚合支付
// pr = integrationPaymentModel.doPay(param);
// } else if (PaymentType.ALIPAY.getPaymentCode().equals(payType)) {
// // 支付宝
// pr = alipayNativePaymentModel.doPay(param);
// }
//
// return pr;
// } catch (Exception e) {
// if (e instanceof BusinessException) {
// throw new BusinessException(e.getMessage());
// } else {
// e.printStackTrace();
// throw new BusinessException(ErrorEnum.SERVER_EXCEPTION.getErrDesc(),
// ErrorEnum.SERVER_EXCEPTION.getErrCode());
// }
// }
// }
//
// }
