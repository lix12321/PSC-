package cn.wellcare.core.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yizhenn on 2016/12/4.
 */
public enum ErrorEnum {
	PARAM_NULL(1, "001", "参数为空"), PARAM_IS_INVALID(2, "002", "无效参数"), SERVER_EXCEPTION(3, "003","服务器繁忙，请稍后重试！"), 
	BUSINESS_EXCEPTION(4, "004", "业务异常，请重新发起请求"), RPC_EXCEPTION(5, "005","rpc exception"), 
	ILLEGAL_REQUEST(6, "006", "非法请求，请检查请求参数是否正确"), UNAUTHORIZED_EXCEPTION(7, "007","机构未授权支付服务，请联系管理员"), 
	ORDER_EXISTS_EXCEPTION(8, "008","订单已存在"), UNSUPORT_PAY_TYPE(9, "009", "不支持的支付方式"),ORDER_NOT_EXISTS_EXCEPTION(10, "010","业务订单不存在");

	private int errId;
	private String errCode;
	private String errDesc;

	private ErrorEnum(int errId, String errCode, String errDesc) {
		this.errId = errId;
		this.errCode = errCode;
		this.errDesc = errDesc;
	}

	public int getFlag() {
		return this.errId;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public String getErrDesc() {
		return this.errDesc;
	}

	@Override
	public String toString() {
		return "ErrorEnum{" + "errCode='" + this.errCode + '\'' + ", errDesc='" + this.errDesc + '\'' + '}';
	}

	public static ErrorEnum valueOf(int errId) {
		return flag2ErrorEnum.get(errId);
	}

	private static final Map<Integer, ErrorEnum> flag2ErrorEnum = new HashMap<>();

	static {
		for (ErrorEnum errorEnum : ErrorEnum.values()) {
			flag2ErrorEnum.put(errorEnum.errId, errorEnum);
		}
	}

}
