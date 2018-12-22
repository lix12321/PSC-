package cn.wellcare.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Component
public class ExceptionResolver implements HandlerExceptionResolver {

	Logger log = Logger.getLogger(this.getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		this.log.error("系统异常：" + ex.getMessage());
		ex.printStackTrace();

		Map<String, String> map = new HashMap<>();

		String msg = "服务器繁忙";
		if (ex instanceof BusinessException) {
			msg = ex.getMessage();
		}
		map.put("请求失败，错误信息", msg);
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView(), map);
		return modelAndView;

	}
}