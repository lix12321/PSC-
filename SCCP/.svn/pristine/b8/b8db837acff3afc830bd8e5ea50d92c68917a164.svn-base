package cn.wellcare.shiro;

import cn.wellcare.constant.Constants;
import cn.wellcare.entity.SystemUsers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 平台Session管理
 * 
 * @author zhaihl
 *
 */
public class SessionManager {

	public static final String SESSION_ADMIN_ORG = "SESSION_ADMIN_ORG";

	/**
	 * 放入Session
	 * 
	 * @param request
	 * @param users
	 */
	public static void putAdminUser(HttpServletRequest request, SystemUsers users) {
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_ADMIN_ORG, users);
	}

	/**
	 * 获取用户验证码
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getVerifyNumber(HttpServletRequest request) throws Exception {
		String verify_number = (String) request.getSession().getAttribute(Constants.VERIFY_NUMBER_NAME);
		return verify_number;
	}

	/**
	 * 取得管理端Session
	 * 
	 * @param request
	 * @return
	 */
	public static SystemUsers getAdminUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SystemUsers adminUser = (SystemUsers) session.getAttribute(SESSION_ADMIN_ORG);
		return adminUser;
	}

	/**
	 * 设置验证码
	 * 
	 * @param request
	 * @param verifyNumber
	 */
	public static void setVerifyNumber(HttpServletRequest request, String verifyNumber) {
		request.getSession(true).setAttribute(Constants.VERIFY_NUMBER_NAME, verifyNumber);
	}

}
