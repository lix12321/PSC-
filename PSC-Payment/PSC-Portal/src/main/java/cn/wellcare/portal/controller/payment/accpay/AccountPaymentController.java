package cn.wellcare.portal.controller.payment.accpay;

import cn.wellcare.api.trade.IPscAccPaymentService;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;
import cn.wellcare.pojo.common.AccPaymentResult;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.pojo.common.ServiceResult;
import cn.wellcare.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RequestMapping(Constants.ACCOUNT_PAY)
@Controller
public class AccountPaymentController extends BaseController {
	@Resource(name = "pscAccPaymentService")
	private IPscAccPaymentService accountPayService;

	@Resource
	private IOrderService orderService;

	@RequestMapping(value = Constants.DOPAY, produces = Constants.CONTENT_TYPE_JSON)
	@ResponseBody
	public ServiceResult<PaymentResult> accpay(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param) {
		ServiceResult<PaymentResult> serviceResult = new ServiceResult<>();
		RpcResult<AccPaymentResult> result = null;
		try {
			// 1.创建订单
			PayOrder order = this.orderService.createOrder(param, new UnifyPaymentInfo() {
				@Override
				public String getOrderType() {
					return PaymentType.ACCOUNT_PAY.getPaymentCode();
				}

				@Override
				public String getPaymentName() {
					return PaymentType.ACCOUNT_PAY.getPaymentName();
				}
			});
			// 2.账户扣款
			result = accountPayService.accPay(param);
			if (!result.isSuccess()) {
				if (Constants.SERVICE_RESULT_CODE_SYSERROR.equals(result.getMsgCode())) {
					throw new RuntimeException(result.getMsgInfo());
				} else {
					throw new BusinessException(result.getMsgInfo());
				}
			}
			order.setOrderState(Integer.valueOf(PayOrder.ORDER_STATE_FINISH));
			order.setPaymentStatus(Integer.valueOf(PayOrder.ORDER_PAY_STATUS_PAEID));
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
			LocalDateTime localDateTime = LocalDateTime.now();
			String date = localDateTime.format(dateTimeFormatter);
			order.setTradeSn("zh" + date);
			order.setMoneyPaidBalance(new BigDecimal(result.getData().getTotalFee()));
			// 2.更新订单
			orderService.updateOrder(order);
			serviceResult.setStatus(Constants.SERVICE_RESULT_SUCCESS);
			serviceResult.setData(new PaymentResult(order.getMoneyOrder().toString(), order.getId(),
					Integer.valueOf(order.getPaySn()), Integer.valueOf(order.getOuterOrderSn())));
		} catch (Exception e) {
			result.setSuccess(false);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					result.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					result.setMsgInfo(e.getMessage());
				result.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				if (e instanceof UnauthorizedException) {
					result.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
				} else {
					e.printStackTrace();
					result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
				}
			}
		}

		return serviceResult;
	}
}
