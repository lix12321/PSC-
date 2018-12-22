package cn.wellcare.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.wellcare.pojo.CommonResponse;
import cn.wellcare.utils.CommonUtils;

@Component
public class ExceptionResolver implements HandlerExceptionResolver {

	Logger log = Logger.getLogger(this.getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		CommonResponse<String> result = new CommonResponse<>();
		try {
			this.log.error("系统异常：" + ex.getMessage());
			ex.printStackTrace();

			Map<String, String> map = new HashMap<>();

			String msg = "服务器繁忙";
			if (ex instanceof BusinessException) {
				msg = ex.getMessage();
			}
			map.put("请求失败，错误信息", msg);

			result.setHasPermit(false);
			if (ex instanceof BusinessException) {
				BusinessException pe = (BusinessException) ex;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					result.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					result.setMsgInfo(ex.getMessage());
				result.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				if (ex instanceof UnauthorizedException) {
					result.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
				} else if (ex instanceof IllegalArgumentException) {
					result.setError(ErrorEnum.PARAM_IS_INVALID);
				} else if (ex instanceof InactiveTimeException) {
					result.setError(ErrorEnum.INACTIVE_TIME_EXCEPTION);
				} else {
					ex.printStackTrace();
					result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView(),
				CommonUtils.obj2Map(result, false, true));
		return modelAndView;

	}
}