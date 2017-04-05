package util;	

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	private Gson gson;

	public JsonUtils() {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getName().contains("_");
			}
			
			public boolean shouldSkipClass(Class<?> clazz) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		gson = gsonBuilder.create();
	}
	
	public JsonUtils(final String... exclude) {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
			public boolean shouldSkipField(FieldAttributes f) {
				if(exclude.length > 0) {
					for (String value : exclude) {
						return value.equalsIgnoreCase(f.getName());
					}
				}
				
				return false;
			}
			
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		});
		
		gson = gsonBuilder.create();
	}

	public <T> T fromJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String normalize(Collection list) {

		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("[");

		Integer countProperties = 0;
		for (Object value : list) {

			if (countProperties > 0) {
				jsonStr.append(",");
			}

			if (value instanceof LinkedHashMap) {
				jsonStr.append(JsonUtils.normalize((LinkedHashMap) value));

			} else if (value instanceof Collection) {
				jsonStr.append(JsonUtils.normalize((Collection) value));

			} else {
				jsonStr.append("\"" + value.toString() + "\"");
			}

			countProperties++;
		}

		jsonStr.append("]");

		return jsonStr.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String normalize(LinkedHashMap<String, String> map) {

		StringBuilder jsonStr = new StringBuilder();
		Iterator<String> keys = map.keySet().iterator();

		jsonStr.append("{");

		Integer countProperties = 0;
		while (keys.hasNext()) {
			Object key = keys.next();
			Object value = map.get(key);

			if (countProperties > 0) {
				jsonStr.append(",");
			}

			jsonStr.append(key.toString());
			jsonStr.append(":");

			if (value instanceof LinkedHashMap) {
				jsonStr.append(JsonUtils.normalize((LinkedHashMap) value));
			} else if (value instanceof Collection) {
				jsonStr.append(JsonUtils.normalize((Collection) value));

			} else {
				if (value != null) {
					jsonStr.append("\"" + value.toString() + "\"");
				}
			}
			countProperties = +1;
		}
		jsonStr.append("}");

		return jsonStr.toString();
	}

	public String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static String toJsonException(Object obj) {
		return new JsonUtils().toJson(obj);
	}
	
	public static String toJsonException(Object obj, String... exclude) {
		return new JsonUtils(exclude).toJson(obj);
	}

	public static String formatarMensagem(String msg) {
		return msg != null && msg.length() > 1000 ? msg.substring(0, 999) : msg;
	}

}