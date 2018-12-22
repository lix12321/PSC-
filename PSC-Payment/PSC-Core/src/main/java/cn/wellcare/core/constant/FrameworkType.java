package cn.wellcare.core.constant;

public enum FrameworkType {
	LOCAL(1), DUBBO(1);

	private int type;

	private FrameworkType(int type) {
		this.setType(type);
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
