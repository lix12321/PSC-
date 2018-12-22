package cn.wellcare.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import cn.wellcare.core.utils.CommonUtils;

public class ResourceCheckFilter extends AccessControlFilter {
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		String uri = getPathWithinApplication(request);
		if (uri != null && uri.endsWith("/")) {
			// 截去url最后一个/
			uri = uri.substring(0, uri.length() - 1);
		}
		log.debug("访问URI" + uri);
		if (CommonUtils.isNull(uri))
			return false;
		return subject.isAuthenticated() && subject.isPermitted(uri);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse hsp = (HttpServletResponse) response;
		HttpServletRequest hReq = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		// 如果没有登录，去登录页
		if (!subject.isAuthenticated()) {
			hsp.getWriter()
					.print("<script>top.window.location.href='" + hReq.getContextPath() + "/admin/login'</script>");
		} else {
			// 如果已登录，提示没有权限
			hsp.sendRedirect(hReq.getContextPath() + "/system/unauth");
		}
		return false;
	}

}
