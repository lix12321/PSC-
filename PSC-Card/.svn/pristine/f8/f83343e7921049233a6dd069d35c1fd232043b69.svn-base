package cn.wellcare.core.constant;

/**
 * 交易日志业务操作类型枚举<br>
 * 对应交易日志操作类型
 * 
 * @author zhaihl
 *
 */
public enum OpType {
	CONSUME(1, "消费"), REFUND(2, "退费 "), RECHARGE(3, "充值"), CASH(4, "提现");

	private int type;
	private String name;

	private OpType(int type, String name) {
		this.setType(type);
		this.setName(name);
	}

	public int getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}


}
