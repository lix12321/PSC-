package cn.wellcare.portal.controller.demo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wellcare.core.constant.Constants;

/**
 * 演示首页
 * 
 * @author zhaihl
 *
 */
@Controller
public class WebViewController {

	@RequestMapping(value = Constants.INDEX_PAGE)
	public String payGateway(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param) {
		return Constants.INDEX_PAGE;
	}

}
