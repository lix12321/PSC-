package cn.wellcare.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.payment.notify.PaymentNotifyRpc;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 基础Controller
 * 
 * @author zhaihl
 *
 */
public class NotifyBaseController extends BaseController {
	protected Logger log = Logger.getLogger(this.getClass());
	protected RpcResult<Boolean> result = null;
	protected static final String STATUC_SUCCESS = "SUCCESS";
	protected static final String STATUC_FAIL = "FAIL";

	protected static void sendMsg(String contentType, HttpServletResponse resp, String return_code) {
		resp.setContentType(contentType);

		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.print(return_code);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	protected static void sendMsg(HttpServletResponse resp, String return_code) {
		sendMsg(Constants.CONTENT_TYPE_HTML, resp, return_code);
	}

	protected void invoke(String beanName, Map<String, Object> params) {
		this.result = new RpcResult<>();
		try {
			PaymentNotifyRpc nr = (PaymentNotifyRpc) ServiceLocator.getInstance().getBean(beanName);
			this.log.debug("实例" + beanName + "：" + nr);
			// TODO others
			this.result.setData(nr.doNotify(params));
		} catch (Exception e) {
			this.result.setData(false);
			this.result.setSuccess(false);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.SERVER_EXCEPTION.getErrCode().equals(pe.getCode()))
					this.result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
				else
					this.result.setMsgInfo(e.getMessage());
			} else {
				e.printStackTrace();
				this.result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
			}
		}
	}

}
