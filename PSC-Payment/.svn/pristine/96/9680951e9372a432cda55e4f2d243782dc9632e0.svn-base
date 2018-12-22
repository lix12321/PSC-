package cn.wellcare.pojo.alipay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	/**
	 * 支付宝，显示订单名称，PC和Mobile公用，一般设置商城的名称
	 */
	public final static String ALIPAY_ALL_SUBJECT = "HIS支付订单";

	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088131362462491";

	// 收款支付宝账号，一般情况下收款账号就是签约账号
	public static String seller_email = "574922780@qq.com";
	// 商户的私钥
	public static String key = "1v6ob9v4exid8r40rzdpzmta82r9qkm4";

	public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzpkR/slPUpdHMon2yI4J9cock8mXgFLCRB19Eacyi0i7Fpglis8YVg1n3wYBGARWjCOsevhaq6oudx8Vql0AB/nH8Qg/QxtpRPYRAz+yVifrv42BsiThHR2gb/YOIONOcSrLZT80NvoBg4pu3sLJgK2P4ENyGfKlJYx4V2LhZMNShujSRm+koWMp6bFHlK6CaeWgHd5pzYuKdZ2iE7OVcvqfOCvwPCpnpFPMx3p1sIv/gZBiHCtEgoPoyM3/8y0uXAY41y0uooPs7FP8/De2feeJsipv8krE+fbdeBkF0bM8AlW5LFWt6h99QPQA4cxfurPp6mZjetP3NhzZjBiY7QIDAQAB";

	/**
	 * 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	 */
	public static String seller_id = partner;

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "MD5";

}
