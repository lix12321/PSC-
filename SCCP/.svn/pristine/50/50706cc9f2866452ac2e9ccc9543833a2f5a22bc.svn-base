package cn.wellcare.api;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wellcare.constant.BaseParam;
import cn.wellcare.constant.Constants;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.exception.ErrorEnum;
import cn.wellcare.exception.InactiveTimeException;
import cn.wellcare.exception.UnauthorizedException;
import cn.wellcare.pojo.CommonResponse;
import cn.wellcare.utils.CommonUtils;
import cn.wellcare.utils.Md5SignUtil;

public class AuthorityBase {

	protected CommonResponse<?> result;
	protected Logger log = Logger.getLogger(this.getClass());

	@ModelAttribute
	public void doSecureity(@RequestParam Map<String, Object> param, HttpServletRequest request) {
		try {
			// 1.参数校验
			String secretkey = (String) param.get(BaseParam.SECRETKEY);
			// 客户端时间
			String requestTime = (String) param.get(BaseParam.REQUEST_TIME);
			String org = (String) param.get(BaseParam.ORG);
			Assert.notNull(secretkey);
			Assert.notNull(org);

			setParams(param);

			// 2.移除签名后做服务端签名
			param.remove(BaseParam.SECRETKEY);
			String sign = Md5SignUtil.sginMD5(CommonUtils.sort(param), Constants.SERVER_KEY);

			param.put(BaseParam.CLIENT_IP, CommonUtils.getRemoteIpAddr(request));
			log.debug("签名：" + sign);

			// 3.比对客户端密钥
			if (!secretkey.equals(sign)) {
				throw new UnauthorizedException();
			}

			// 4.请求时间不超过15s
			long now = new Date().getTime();
			log.debug("服务器时间：" + now);
			long diff = now - Long.valueOf(requestTime);
			long seconds = diff / 1000;
			if (seconds > Constants.SERVER_ACCEPT_MAX_SECOND) {
				throw new InactiveTimeException();
			}

		} catch (Exception e) {
			throw e;
		}
	}

	protected void setParams(Map<String, Object> param) {
	}

	private void check() {
		if (result == null) {
			result = new CommonResponse<>();
		}
	}

	public void requestFail(Exception e) {
		check();
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
			} else if (e instanceof IllegalArgumentException) {
				result.setError(ErrorEnum.PARAM_IS_INVALID);
			} else if (e instanceof InactiveTimeException) {
				result.setError(ErrorEnum.INACTIVE_TIME_EXCEPTION);
			} else {
				e.printStackTrace();
				result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
			}
		}
	}

	public void setRequestURI(Map<String, Object> param) {
		check();
		result.setRequestURI((String) param.get(BaseParam.URI));
	}
}
