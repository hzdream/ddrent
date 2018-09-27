/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.json 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 下午2:23:02
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.json.adapter;

import java.io.IOException;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/** 
 * @ClassName: GsonUtil 
 * @Description: json 工具
 * @author: imart·deng
 * @date: 2018年9月18日 下午2:23:02  
 */
public class EmptyStringToNumberTypeAdapter extends TypeAdapter<Number> {

	/* (non-Javadoc)
	 * @see com.google.gson.TypeAdapter#write(com.google.gson.stream.JsonWriter, java.lang.Object)
	 */
	@Override
	public void write(JsonWriter writer, Number value) throws IOException {
		if (value == null) {
			writer.nullValue();
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
