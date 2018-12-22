package cn.wellcare.core.bean;

/**
 * 系统域名
 * 
 * @author zhaihl
 *
 */
public class DomainUrlUtil {
	public static String PSC_PAYMENT_URL;
	public static String PSC_ACCOUNT_URL;

	public void setPSC_PAYMENT_URL(String pSC_PAYMENT_URL) {
		PSC_PAYMENT_URL = pSC_PAYMENT_URL;
	}

	public void setPSC_ACCOUNT_URL(String pSC_ACCOUNT_URL) {
		PSC_ACCOUNT_URL = pSC_ACCOUNT_URL;
	}

	public static String getPSC_PAYMENT_URL() {
		return PSC_PAYMENT_URL;
	}

	public static String getPSC_ACCOUNT_URL() {
		return PSC_ACCOUNT_URL;
	}

}
