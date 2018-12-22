package cn.wellcare.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomUtil {
	private static final Random RANDOM = new Random();
	// 定义验证码字符.去除了O和I等容易混淆的字母
	public static final char ALPHA[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'G', 'K', 'M', 'N', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 产生两个数之间的随机数
	 * 
	 * @param min
	 *            小数
	 * @param max
	 *            比min大的数
	 * @return int 随机数字
	 */
	public static int num(int min, int max) {
		return min + RANDOM.nextInt(max - min);
	}

	/**
	 * 产生0--num的随机数,不包括num
	 * 
	 * @param num
	 *            数字
	 * @return int 随机数字
	 */
	public static int num(int num) {
		return RANDOM.nextInt(num);
	}

	public static char alpha() {
		return ALPHA[num(0, ALPHA.length)];
	}

	/** 年月日时分秒(无下划线) yyMMddHHmmss */
	public static final String DATE_FORMAT = "yyMMddHHmmssSSS";
	private static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', 'P', 'N' };

	/**
	 * 获取count个随机数
	 * 
	 * @param count
	 *            随机数个数
	 * @return
	 */
	public static String randomNumber(int count) {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();

		// 所有集合数据
		List<String> list = new ArrayList<>();
		// 数字
		for (int i = 0; i < 10; i++) {
			list.add(i + "");
		}
		// 字母
		for (char i = 'a'; i <= 'z'; i++) {
			list.add(i + "");
		}
		// 随机取数
		int max = list.size();
		for (int i = 0; i < count; i++) {
			sb.append(list.get(r.nextInt(max)));
		}
		return sb.toString();
	}

	/**
	 * 获取count个随机数
	 * 
	 * @param count
	 *            随机数个数
	 * @return
	 */
	public static String randomNumberic(int count) {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int num = r.nextInt(10);
			sb.append(num);
		}
		return sb.toString();
	}

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyMMddHHmmss为格式的当前系统时间+2位随机数
	 */
	public static String getOrderSn() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		String sn = df.format(date);
		sn += randomNumber(2);
		return sn;
	}

	private static String toUnsignedString(long i, int shift) {
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << shift;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (i & mask)];
			i >>>= shift;
		} while (i != 0);
		return new String(buf, charPos, (64 - charPos));
	}

	// j为2的次方，如转成16进制就是4，32进制就是5...
	public static String getRand(long i, int j) {
		return toUnsignedString(i, j);
	}

	// 随机码＋时间戳＋随机码的生成
	public static Long getRand() {
		String str1, str2, str3;
		str1 = getRandStr(2);
		str3 = getRandStr(3);
		str2 = (new Date()).getTime() + "";
		return Long.parseLong(str1 + str2 + str3);
	}

	// 主键生成
	public static String getKey() {
		return getRand(getRand(), 6);
	}

	// 生成指定长度的随机串
	public static String getRandStr(Integer length) {
		String str = "";
		while (str.length() != length) {
			str = (Math.random() + "").substring(2, 2 + length);
		}
		return str;
	}
}
