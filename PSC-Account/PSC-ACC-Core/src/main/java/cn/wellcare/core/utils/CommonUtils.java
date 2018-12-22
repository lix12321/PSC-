package cn.wellcare.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
	/**
	 * 获取客户端Ip
	 * 
	 * 通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。 获得符合
	 * <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
	 * 
	 * @return
	 */
	public static String localIp() {
		String ip = null;
		Enumeration<NetworkInterface> allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = allNetInterfaces.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();

				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}

			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ip;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
	 * 
	 * @return ip
	 */
	public static String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static boolean isNull(Object... objs) {
		if (objs == null)
			return true;
		for (Object obj : objs) {
			if (obj == null || "".equals(obj) || "null".equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取所有requeste参数
	 * 
	 * @param req
	 * @return
	 */
	public static Map<String, Object> getParam(HttpServletRequest req) {
		Map<String, String[]> param = req.getParameterMap();
		Map<String, Object> map = new HashMap<>(param.size());

		param.forEach((k, v) -> {
			if (!isNull(v[0]))
				map.put(k, v[0]);
		});
		return map;
	}

	/**
	 * 产生随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度

		StringBuffer base = new StringBuffer();
		for (char i = 'a'; i < 'z'; i++) {
			base.append(i);
		}
		for (int i = 0; i < 10; i++) {
			base.append(i);
		}
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String getString(Object msg) {
		if (msg != null) {
			return msg.toString();
		} else {
			return null;
		}
	}

	/**
	 * 对象转字节(序列化)
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] objectToByte(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bo = null;
		ObjectOutputStream oo = null;
		try {
			bo = new ByteArrayOutputStream();
			oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bo != null)
					bo.close();
				if (oo != null)
					oo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

	/**
	 * 字节转对象（反序列化）
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byteToObject(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream bi = null;
		ObjectInputStream oi = null;
		try {
			bi = new ByteArrayInputStream(bytes);
			oi = new ObjectInputStream(bi);

			obj = oi.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bi != null)
					bi.close();
				if (oi != null)
					oi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	public static Map<String, String> obj2Map(Object obj) {
		Map<String, String> map = new HashMap<String, String>();
		// 获取f对象对应类中的所有属性域
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			String varName = fields[i].getName();
			try {
				// 获取原来的访问控制权限
				boolean accessFlag = fields[i].isAccessible();
				// 修改访问控制权限
				fields[i].setAccessible(true);
				// 获取在对象f中属性fields[i]对应的对象中的变量
				Object o = fields[i].get(obj);
				if (o != null)
					map.put(varName, o.toString());
				// 恢复访问控制权限
				fields[i].setAccessible(accessFlag);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 如果为null，转换为""
	 * 
	 * @param value
	 * @return
	 */
	public static String nullSafeString(String value) {
		return value == null ? "" : value;
	}

	/**
	 * 确保存入数据库的string值不会引起数据库报错。
	 * <p>
	 * 1. 数据库不允许为null，value为nul时返回""；<br />
	 * 2. 超过最大长度时截断字符串。
	 * 
	 * @param value 要存入数据库的字符串值。
	 * @param nullable 是否允许为null。
	 * @param maxLength 最大长度。
	 * @return
	 */
	public static String dbSafeString(String value, boolean nullable, int maxLength) {
		if (value == null) {
			if (nullable)
				return null;
			return nullSafeString(value);
		}
		if (value.length() > maxLength)
			return value.substring(0, maxLength);
		return value;
	}

	public static String getMethod(String field) {
		if (isNull(field))
			return null;
		String prefix = "get";
		String firstLit = field.substring(0, 1).toUpperCase();
		String afterLit = field.substring(1);
		return prefix + firstLit + afterLit;
	}

}
