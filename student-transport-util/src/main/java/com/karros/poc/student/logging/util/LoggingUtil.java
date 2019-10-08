package com.karros.poc.student.logging.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoggingUtil {
	
	public static String printKeyValueLog(final String key, final Object value, final Object ... alternatingKeyValue) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put(key, value);
		for(int keyValueIterator = 0; (keyValueIterator + 2) <= alternatingKeyValue.length; keyValueIterator += 2){
			if(alternatingKeyValue[keyValueIterator] instanceof String) {
				map.put((String) alternatingKeyValue[keyValueIterator],
						alternatingKeyValue[keyValueIterator + 1]);
			}
		}
		return map.toString();
	}

}
