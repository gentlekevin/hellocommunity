package com.btict.rest;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToMapUtil {
 
	
	public static Map<String,String> MapFromJSON(String json){
		ObjectMapper mapper = new ObjectMapper(); 
		Map map =null;
		try {
			 map = 	 mapper.readValue(json, Map.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
