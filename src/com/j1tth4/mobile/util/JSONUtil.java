package com.j1tth4.mobile.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class JSONUtil {
	private Gson gson;
	
	public JSONUtil(){
		gson = new Gson();
	}
	
	public Object toObject(Type type, String json){
		return gson.fromJson(json, type);
	}
}
