package cn.wellcare.admin.aop;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.wellcare.bo.SystemUsersBO;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.utils.CommonUtils;

@Component
@Aspect
@SuppressWarnings("unchecked")
public class ParamAspect {
	private Logger log = Logger.getLogger(this.getClass());

	@Pointcut("execution(* cn.wellcare.service..*.page(..))")
	public void paramAspect() {
	}

	@Before("paramAspect())")
	public void doValidate(JoinPoint jp) {
		PrincipalCollection pi = SecurityUtils.getSubject().getPrincipals();
		if (pi == null) {
			// 初始化或未登录时不处理
			log.debug("用户为空，不做处理");
			return;
		}

		SystemUsersBO user = (SystemUsersBO) pi.getPrimaryPrincipal();
		for (Object param : jp.getArgs()) {
			log.debug("参数：" + param);
			if (param instanceof Map) {
				Map<String, Object> querymap = (Map<String, Object>) param;
				if (Constants.SYSTEM_ADMIN_ROLE.toUpperCase().equals(user.getRoleCode())
						&& !CommonUtils.isNull(querymap.get(BaseParam.ORG_ID))) {
					log.debug("管理员移除机构参数");
					querymap.put(BaseParam.ORG_ID, null);
				}
			}
		}
	}

}
