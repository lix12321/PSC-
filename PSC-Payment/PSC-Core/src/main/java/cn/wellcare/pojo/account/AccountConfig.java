package cn.wellcare.pojo.account;

import cn.wellcare.core.bean.DomainUrlUtil;
import cn.wellcare.core.constant.Constants;

/**
 * 账户配置
 * 
 * @author zhaihl
 *
 */
public class AccountConfig {
	public static String NOTIFY_URL = DomainUrlUtil.PSC_PAYMENT_URL + "/" + Constants.ACCOUNT_PAY + "/"
			+ Constants.ACCOUNT_NOTIFY;
}
