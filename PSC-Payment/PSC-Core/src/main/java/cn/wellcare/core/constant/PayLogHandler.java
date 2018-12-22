package cn.wellcare.core.constant;

/**
 * 支付日志操作
 * 
 * @author zhaihl
 *
 */
public enum PayLogHandler {
	CREATE("创建支付日志"), UPDATE("更新支付日志");

	private String name;

	private PayLogHandler(String name) {
		this.setName(name);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
