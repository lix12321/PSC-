package cn.wellcare.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import cn.wellcare.exception.JonException;

/**
 * json 工具类
 * 
 * @author zhaihl
 *
 */
public class JsonUtil {
	/**
	 * json转对象的mapper，前台一般默认只传年月日
	 */
	private static ObjectMapper parseObjectMapper = getObjectMapper("yyyyMMddHHmmss");

	/**
	 * 对象转json的mapper，后台默认返回的日期格式
	 */
	private static ObjectMapper formatObjectMapper = getObjectMapper("yyyyMMddHHmmss");

	private static ObjectMapper getObjectMapper(String dateStyle) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat((new SimpleDateFormat(dateStyle)));
		// 允许字段名字不使用引号
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 允许单引号
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		// 忽略未识别的参数(解决json串中多属性，而类中又未定义的情况)
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

		return objectMapper;
	}

	/**
	 * 将规定对象转换成json字符串。日期类型按照 yyyyMMddHHmmss 的格式转换成字符串
	 * 
	 * @param obj
	 *            对象
	 * @return json字符串
	 */
	public static String writeValueAsString(Object obj) {
		try {
			return formatObjectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new JonException(e);
		}
	}

	/**
	 * 将规定对象转换成json字符串。日期类型按照给定的样式转换
	 * 
	 * @param obj
	 *            对象
	 * @param dateStyle
	 *            日期格式
	 * @return json字符串
	 */
	public static String writeValueAsString(Object obj, String dateStyle) {
		ObjectMapper objectMapper = getObjectMapper(dateStyle);
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new JonException(e);
		}
	}

	/**
	 * 将json字符串转为对象
	 * 
	 * @param <T>
	 *            返回类型
	 * @param content
	 *            json字符串
	 * @param valueType
	 *            返回类型
	 * @return 对象
	 */
	public static <T> T readValue(String content, Class<T> valueType) {
		try {
			return parseObjectMapper.readValue(content, valueType);
		} catch (Exception e) {
			throw new JonException(e);
		}
	}

	/**
	 * 将json转换成对象
	 * 
	 * @param content
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
		try {
			// parseObjectMapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
			// true);
			return parseObjectMapper.readValue(content, valueTypeRef);
		} catch (Exception e) {
			throw new JonException(e);
		}
	}

	/**
	 * 将json转换成对象
	 * 
	 * @param content
	 * @param valueTypeRef
	 * @param dateStyle
	 * @return
	 */
	public static <T> T readValue(String content, TypeReference<T> valueTypeRef, String dateStyle) {
		try {
			ObjectMapper objectMapper = getObjectMapper(dateStyle);
			return objectMapper.readValue(content, valueTypeRef);
		} catch (Exception e) {
			throw new JonException(e);
		}
	}

	/**
	 * 将json字符串转为对象，日期类型按照给定的样式转换
	 * 
	 * @param <T>
	 *            返回类型
	 * @param content
	 *            json字符串
	 * @param valueType
	 *            返回类型
	 * @param dateStyle
	 *            日期格式
	 * @return 对象
	 */
	public static <T> T readValue(String content, Class<T> valueType, String dateStyle) {
		try {
			ObjectMapper objectMapper = getObjectMapper(dateStyle);
			return objectMapper.readValue(content, valueType);
		} catch (Exception e) {
			throw new JonException(e);
		}
	}

	public static <T> T readValue(JsonNode jsonNode, Class<T> valueType) {
		try {
			return parseObjectMapper.readValue(jsonNode, valueType);
		} catch (Exception e) {
			throw new JonException(e);
		}
	}

	public static <T> T readValue(JsonNode jsonNode, TypeReference<T> valueTypeRef) {
		try {
			return parseObjectMapper.readValue(jsonNode, valueTypeRef);
		} catch (Exception e) {
			throw new JonException(e);
		}
	}

	public static <T> T readValue(JsonNode jsonNode, TypeReference<T> valueTypeRef, String dateStyle) {
		ObjectMapper objectMapper = getObjectMapper(dateStyle);
		try {
			return objectMapper.readValue(jsonNode, valueTypeRef);
		} catch (IOException e) {
			throw new JonException(e);
		}
	}

	/**
	 * 根据给定的路径，返回对应的 JsonNode对象
	 * 
	 * @param jsonNode
	 * @param path
	 *            路径表达式。 例如：对于{param:{transCode:"123"}}
	 *            中的transCode可以这样表达,getJsonNode(jsonNode,"param.transCode")
	 * @return
	 */
	public static JsonNode getJsonNode(JsonNode jsonNode, String path) {
		String[] pathArr = path.split("\\.");
		JsonNode temp = jsonNode;
		for (String filed : pathArr) {
			temp = temp.get(filed);
		}
		return temp;
	}

	/**
	 * 得到给定属性的值
	 * 
	 * @param jsonNode
	 * @param path
	 * @return
	 */
	public static String getFieldValue(JsonNode jsonNode, String path) {
		JsonNode node = getJsonNode(jsonNode, path);
		if (node == null) {
			return null;
		}
		return node.asText();
	}

	/**
	 * 不改变原接口，只能做如下扩展
	 * 
	 * @param parseObjectMapper
	 *            parseObjectMapper
	 */
	public static void setParseObjectMapper(ObjectMapper parseObjectMapper) {
		JsonUtil.parseObjectMapper = parseObjectMapper;
	}

}
