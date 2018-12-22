package cn.wellcare.shiro;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;

import cn.wellcare.bo.SystemUsersBO;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.pojo.Users;
import cn.wellcare.sdk.authroity.MenuPermit;
import cn.wellcare.sdk.user.UserInfo;
import cn.wellcare.support.EnumerateParameter;

/**
 * shiro权限realm
 * 
 * @author zhaihl
 *
 */
public class ShiroDbRealm extends AuthorizingRealm {

	Logger log = Logger.getLogger(this.getClass());

	/**
	 * Shiro登录认证(原理：机构提交 机构名和密码 --- shiro 封装令牌 ---- realm 通过机构名将密码查询返回 ---- shiro
	 * 自动去比较查询出密码和机构输入密码是否一致---- 进行登陆控制 )
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		log.info("Shiro开始登录认证");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		Users authuser = new UserInfo().getUserByName(token.getUsername());
		if (authuser == null) {
			log.error("账户不存在");
			return null;
		}
		// 账号未启用
		if (authuser.getDeleted().intValue() == Integer.valueOf(EnumerateParameter.ONE)) {
			log.error("账号是非正常状态：" + token.getUsername());
			throw new DisabledAccountException();
		}
		SystemUsersBO user = new SystemUsersBO();
		BeanUtils.copyProperties(authuser, user);
		// 认证缓存信息
		return new SimpleAuthenticationInfo(user, user.getPwd().toCharArray(), getName());

	}

	/**
	 * Shiro权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		log.debug("---------------------doGetAuthorizationInfo start" + new Date());
		SystemUsersBO org = (SystemUsersBO) principals.getPrimaryPrincipal();

		log.debug("认证机构：" + org);

		List<String> urls = new MenuPermit(org.getId() + "").getMenuPermit();

		if (urls == null || urls.size() == 0) {
			log.error("该机构所属角色没有任何权限");
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(urls);
		log.debug("---------------------doGetAuthorizationInfo end" + urls);

		return info;
	}

	/**
	 * 管理员不验证
	 */
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		SystemUsersBO user = (SystemUsersBO) principals.getPrimaryPrincipal();
		return Constants.SYSTEM_ADMIN_ROLE.equals(user.getRoleCode()) || super.isPermitted(principals, permission);
	}

	@Override
	public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
		SystemUsersBO user = (SystemUsersBO) principals.getPrimaryPrincipal();
		return Constants.SYSTEM_ADMIN_ROLE.equals(user.getRoleCode()) || super.hasRole(principals, roleIdentifier);
	}

}
