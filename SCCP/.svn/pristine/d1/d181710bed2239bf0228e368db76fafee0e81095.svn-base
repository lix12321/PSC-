package cn.wellcare.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.wellcare.constant.Constants;

/**
 * http请求工具类
 * 
 * @author zhaihl
 *
 */
public class HttpClientUtil {
	private static Logger log = Logger.getLogger(HttpClientUtil.class);
	private static final int DEFAULT_TIMEOUT = 2000;

	/**
	 * 模拟一个http请求，并将当前请求的cookie信息带入
	 * 
	 * @param url
	 * @param cookie
	 * @param timeout
	 * @return
	 */
	public static String doGet(String url, Cookie cookie, long timeout) {
		HttpGet get = null;
		CloseableHttpResponse resp = null;
		CloseableHttpClient client = null;
		try {
			client = HttpClients.createDefault();
			get = new HttpGet(url);

			if (cookie != null) {
				get.setHeader("Connection", "keep-alive");
				get.addHeader(new BasicHeader("Cookie", cookie.getName() + "=" + cookie.getValue()));
			}

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
			get.setConfig(requestConfig);

			resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				HttpEntity entity = resp.getEntity();
				String content = EntityUtils.toString(entity, "utf-8");
				return content;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resp != null) {
					resp.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String doGet(String url, int timeout) {
		return doGet(url, null, timeout);
	}

	public static String doGet(String url) {
		return doGet(url, null, DEFAULT_TIMEOUT);
	}

	public static String doPost(String url, Map<String, Object> map, int timeout) {
		return doPost(url, map, Constants.DEFAULT_CHARSET, timeout);
	}

	public static String doPost(String url, Map<String, Object> map) {
		return doPost(url, map, Constants.DEFAULT_CHARSET, DEFAULT_TIMEOUT);
	}

	public static String doPost(String url, Map<String, Object> map, String charset, int timeout) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
					.build();// 设置请求和传输超时时间
			httpPost.setConfig(requestConfig);

			// 设置参数
			if (map != null && map.size() > 0) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> elem = iterator.next();
					BasicNameValuePair bp = new BasicNameValuePair(elem.getKey(), transData(elem.getValue()));
					list.add(bp);
					log.debug("参数:" + bp);
				}
				if (list.size() > 0) {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
					httpPost.setEntity(entity);
				}
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private static String transData(Object value) {
		if (value instanceof String) {
			return (String) value;
		} else if (value instanceof List) {
			List<Object> list = (List<Object>) value;
			return new Gson().toJson(list, new TypeToken<List<Object>>() {
			}.getType());
		} else if (value instanceof Object) {
			return new Gson().toJson(value);
		}
		return null;
	}

}
