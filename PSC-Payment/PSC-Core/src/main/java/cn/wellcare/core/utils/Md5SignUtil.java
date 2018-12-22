package cn.wellcare.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import cn.wellcare.core.constant.Constants;

/**
 * Md5签名工具
 * 
 * @author zhaihl
 *
 */
public class Md5SignUtil {
	static Logger log = Logger.getLogger(Md5SignUtil.class);

	public static String sginMD5(Map<String, Object> map, String key) {
		String prestr = createLinkString(map);// 把数组参数 按照 “参数？参数值的模式？”字符拼接成字符
		return sign(prestr, key, Constants.DEFAULT_CHARSET);
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	private static String createLinkString(Map<String, Object> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = String.valueOf(params.get(key));
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		log.debug("查询字符串：" + prestr);
		return prestr;
	}

	/**
	 * 按默认编码自定义签名
	 * 
	 * @param text
	 * @param key
	 * @return
	 */
	public static String sign(String text, String key) {
		return sign(text, key, Constants.DEFAULT_CHARSET);
	}

	/**
	 * 签名字符串
	 * 
	 * @param text
	 *            需要签名的字符串
	 * @param key
	 *            密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String key, String input_charset) {
		text = text + key;
		log.debug("要签名的字符串：" + text);
		return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不正确,您目前指定的编码集是:" + charset);
		}
	}

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex(getContentBytes(
				"authCode=&deviceInfo=&encryptPwd=1&encryptUser=1&handleName=机构管理员&handleNum=admin&orderID=&orgId=1&payAmount=20.00&payType=011&rcType=008&tradeTime=1545285422784&userId=62652e10adc3949ba59abbe56e057f20f883e",
				Constants.DEFAULT_CHARSET)));
	}
}
