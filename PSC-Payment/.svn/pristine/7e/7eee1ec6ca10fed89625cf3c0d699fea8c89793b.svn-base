// package cn.wellcare.model.payment.account;
//
// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.Map;
//
// import javax.annotation.Resource;
//
// import org.apache.log4j.Logger;
// import org.springframework.stereotype.Component;
//
// import cn.wellcare.core.constant.BaseParam;
// import cn.wellcare.core.constant.PayLogHandler;
// import cn.wellcare.core.constant.PaymentType;
// import cn.wellcare.core.constant.annotations.PaymentLog;
// import cn.wellcare.core.exception.BusinessException;
// import cn.wellcare.core.exception.ErrorEnum;
// import cn.wellcare.core.utils.CommonUtils;
// import cn.wellcare.entity.account.PscPiAccDetail;
// import cn.wellcare.entity.order.PayOrder;
// import cn.wellcare.model.payment.support.accountpay.base.AccountPayment;
// import cn.wellcare.order.IOrderService;
// import cn.wellcare.pojo.account.PscPiAcc;
// import cn.wellcare.pojo.common.PaymentResult;
// import cn.wellcare.pojo.common.ServiceResult;
// import cn.wellcare.support.EnumerateParameter;
// @Component
// public class AccountPayModel extends AccountPayment {
//
// protected Logger log = Logger.getLogger(this.getClass());
// @Resource
// private PscPiAccModel piAccModel;
// @Resource
// private PscPiAccDetailModel pscPiAccDetailModel;
// @Resource
// private IOrderService orderService;
//
// @PaymentLog(PayLogHandler.CREATE)
// public PaymentResult doPay(Map<String,Object> param) {
// try {
// // 1.支付前操作
// PayOrder po = payBefore(param);
// if (CommonUtils.isNull(po)) {
// throw new BusinessException("创建订单失败");
// }
//
// ///////////
// String pkPi = (String) param.get(BaseParam.USER_ID);//获取用户主索引
// BigDecimal amount = new
// BigDecimal(String.valueOf(param.get(BaseParam.PAY_AMOUNT)));//获取账户支付金额
// Integer pkOrg = po.getOrgId();
// PscPiAcc pscPiAcc = piAccModel.getPscPiAccBypkPi(pkPi);//获取用户账户信息
// if ((pscPiAcc.getAmtAcc().add(pscPiAcc.getCreditAcc())).compareTo(amount)<0)
// {
// throw new BusinessException("账户余额不足");
// }
// pscPiAcc.setAmtAcc(pscPiAcc.getAmtAcc().subtract(amount));//余额扣除支付金额
// piAccModel.updatePscPiAcc(pscPiAcc);//更新账户信息
//
// PscPiAccDetail pscPiAccDetail = new PscPiAccDetail();
// //pscPiAccDetail.setPkEmpOpera(); //操作人
// pscPiAccDetail.setPkOrg(pkOrg);
// pscPiAccDetail.setPkPiacc(pscPiAcc.getPkPiacc());
// pscPiAccDetail.setAmtBalance(pscPiAcc.getAmtAcc());
// pscPiAccDetail.setAmount(amount);
// pscPiAccDetail.setEuOptype(EnumerateParameter.TWO);
// pscPiAccDetail.setEuDirect(Integer.valueOf(EnumerateParameter.NEGA));
// pscPiAccDetailModel.savePscPiAccDetail(pscPiAccDetail);//新增账户流水
// ///////////
//
// ServiceResult<PayOrder> payOrder =
// orderService.getOrderBySn(po.getOuterOrderSn());//查询订单信息
// LocalDateTime localDateTime=LocalDateTime.now();
// DateTimeFormatter
// dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
// String data = localDateTime.format(dateTimeFormatter);
//
// PayOrder order = payOrder.getData();
// order.setOrderState(Integer.valueOf(EnumerateParameter.SIX));
// order.setPaymentStatus(Integer.valueOf(EnumerateParameter.ONE));
// order.setTradeSn("zh"+data);
// order.setMoneyPaidBalance(pscPiAcc.getAmtAcc());
// orderService.updateOrder(order); //更新订单状态
//
// return new PaymentResult(amount.toString(),po.getPaySn());
//
// }catch (Exception e) {
//
// if (e instanceof BusinessException) {
// throw new BusinessException(e.getMessage());
// } else {
// e.printStackTrace();
// throw new BusinessException(ErrorEnum.SERVER_EXCEPTION.getErrDesc(),
// ErrorEnum.SERVER_EXCEPTION.getErrCode());
// }
// }
//
// }
//
// @Override
// protected String getaccountPayOrderType() {
// return PaymentType.ACCOUNT_PAY.getPaymentCode();
// }
//
// @Override
// protected String getaccountPayPaymentName() {
// return PaymentType.ACCOUNT_PAY.getPaymentName();
// }
// }
