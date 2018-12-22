package cn.wellcare.core.constant;

/**
 * 支付签名类型
 * 
 * @author zhaihl
 *
 */
public enum SignType {
	/**
	 * md5摘要签名
	 */
	MD5("MD5"),
	/**
	 * rsa签名
	 */
	RSA("RSA");

	private String name;

	private SignType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
