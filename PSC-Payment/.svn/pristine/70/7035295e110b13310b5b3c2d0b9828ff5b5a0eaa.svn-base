package cn.wellcare.portal.controller.demo;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wellcare.api.acc.IPscPiAccService;
import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PaymentType;

/**
 * 演示首页
 * 
 * @author zhaihl
 *
 */
@Controller
@RequestMapping("demo/view")
public class DemoController {

	@ModelAttribute
	public void before(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.put("time", new Date().getTime());
	}

	@RequestMapping("unifyPay")
	public String demopay(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String payType) {
		model.put("payType", payType);
		String viewname = "demo/unifypay";
		if (PaymentType.ACCOUNT_PAY.getPaymentCode().equals(payType)) {
			viewname = "demo/accconsume";
		}
		return viewname;
	}

	@RequestMapping("accPay")
	public String accPay(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "demo/accpay";
	}

	@RequestMapping("misPos")
	public String misPos(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "demo/mispos";
	}

	@RequestMapping(value = Constants.GET_ACCAMOUNT, produces = Constants.CONTENT_TYPE_JSON)
	@ResponseBody
	public BigDecimal getAccAmount(HttpServletRequest request, HttpServletResponse response, String userId) {
		// TODO 查询账户-->余额(消费前/后的余额)
		IPscPiAccService ph = null;
		ph = (IPscPiAccService) ServiceLocator.getInstance().getBean("pscPiAccService");
		BigDecimal amount = new BigDecimal(0);
		cn.wellcare.acc.entity.PscPiAcc pscPiAcc = ph.getPscPiAccBypkPi(userId).getData();
		if (pscPiAcc!=null){
			amount = pscPiAcc.getAmtAcc();
		}
		return amount;
	}

	@RequestMapping("refundPay")
	public String demorefundpay(HttpServletRequest request, HttpServletResponse response, ModelMap model
						  ,@RequestParam String payType) {
		model.put("payType", payType);
		return "demo/refundPay";
	}

	@RequestMapping("querypay")
	public String querypay(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String payType) {
		model.put("payType", payType);
		return "demo/querypay";
	}
}
