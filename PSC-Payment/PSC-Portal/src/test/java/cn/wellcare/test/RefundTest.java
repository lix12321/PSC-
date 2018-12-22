package cn.wellcare.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.wellcare.core.utils.HttpClientUtil;

public class RefundTest {
	final String URL = "http://localhost:8080/unifiedRefund/refundPay";

	@Test
	public void testRefund() {
		Map<String, Object> param = new HashMap<>();
		param.put("outRequestNo", "HZ00000001");
		param.put("encryptUser", "123");
		param.put("tradeTime", "20181129");
		param.put("payType", "001");
		param.put("refundReason", "我是中文");
		param.put("outTradeNo", "777777777777777");
		param.put("sign", "4935adf32c99cb7255981f168e9d9177");
		param.put("userId", "1");
		param.put("orgId", "121");
		param.put("encryptPwd", "123");
		param.put("refundAmount", "0.01");

		String result = HttpClientUtil.doPost(URL, param);
		System.out.println(result);
	}
}
