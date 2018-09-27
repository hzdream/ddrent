/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.data 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 上午11:03:56
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.data;

import java.io.IOException;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/** 
 * @ClassName: StringUtils 
 * @Description: 字符串工具类
 * @author: imart·deng
 * @date: 2018年9月18日 上午11:03:56  
 */
public class GsonUtil {
	
	static final Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat("yyyy-MM-dd HH:mm:ss")
			.registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(long.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(Long.class, new EmptyStringToNumberTypeAdapter())
			.create();
	
	public static class EmptyStringToNumberTypeAdapter extends TypeAdapter<Number> {
		/* (non-Javadoc)
		 * @see com.google.gson.TypeAdapter#write(com.google.gson.stream.JsonWriter, java.lang.Object)
		 */
		@Override
		public void write(JsonWriter writer, Number value) throws IOException {
			if (value == null) {
				writer.jsonValue("");
				return;
			}
			writer.value(value);
		}

		/* (non-Javadoc)
		 * @see com.google.gson.TypeAdapter#read(com.google.gson.stream.JsonReader)
		 */
		@Override
		public Number read(JsonReader reader) throws IOException {
			if (reader.peek() == JsonToken.NULL) {
				reader.nextNull();
				return null;
			}
			String stringValue = reader.nextString();
			try {
				Number value = NumberUtils.createNumber(stringValue);
				return value;
			} catch (NumberFormatException e) {
				return null;
			}
		}
	}
	
	/**
	 *<strong>获取gson工具</strong>
	 * <ul>
	 * 	<li>null --> ""</li>
	 * 	<li>Date pattern --> "yyyy-MM-dd HH:mm:ss"</li>
	 * </ul>
	 * @return
	 */
	public static Gson gson() {
		return gson;
	}
}
