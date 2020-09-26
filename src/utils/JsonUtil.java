package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {

	
	/**
	 * 〈简述〉获取json字符串
	 * 〈详细描述〉
	 * 
	 * @param Object  对象
	 * @return jsonstr json字符串
	 */
	public static String parseString(Object obj) {
		Gson gson=new Gson();
		String jsonstr=gson.toJson(obj);
		return jsonstr;
	}
	
	
	
	/**
	 * 〈简述〉获取JsonObject
	 * 〈详细描述〉
	 * 
	 * @param json  json字符串
	 * @return JsonObject json对象
	 */
	public static JsonObject parseJson(String json) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = parser.parse(json).getAsJsonObject();
		return jsonObj;
	}

	/**
	 * 〈简述〉根据json字符串返回Map对象
	 * 〈详细描述〉
	 * 
	 * @param json
	 *            json字符串
	 * @return Map<String,Object> map集合
	 */
	public static Map<String, Object> toMap(String json) {
		return JsonUtil.toMap(JsonUtil.parseJson(json));
	}

	/**
	 * 〈简述〉将JSONObjec对象转换成Map-List集合
	 * 〈详细描述〉
	 * 
	 * @param json
	 *            json对象
	 * @return Map<String, Object> map集合
	 */
	public static Map<String, Object> toMap(JsonObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<Entry<String, JsonElement>> entrySet = json.entrySet();
		for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext();) {
			Entry<String, JsonElement> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof JsonArray)
				map.put((String) key, toList((JsonArray) value));
			else if (value instanceof JsonObject)
				map.put((String) key, toMap((JsonObject) value));
			else
				map.put((String) key, value);
		}
		return map;
	}

	/**
	 * 〈简述〉将JSONArray对象转换成List集合
	 * 〈详细描述〉
	 * 
	 * @param json
	 *            jsonArray对象
	 * @return List<Object>
	 */
	public static List<Object> toList(JsonArray json) {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < json.size(); i++) {
			Object value = json.get(i);
			if (value instanceof JsonArray) {
				list.add(toList((JsonArray) value));
			} else if (value instanceof JsonObject) {
				list.add(toMap((JsonObject) value));
			} else {
				list.add(value);
			}
		}
		return list;
	}

}
