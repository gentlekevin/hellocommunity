
package com.btict.rest;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 专用于Restful Service的异常.
 * 
 * @author calvin
 */
public class RestException extends RuntimeException {

	public HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	public RestException() {
	}

	public RestException(HttpStatus status) {
		this.status = status;
	}

	public RestException(String message) {
		
		super(message);
	}

	public RestException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
	
	public static String errorResult(String message) {
		ObjectMapper mapper = new ObjectMapper();
		Map map = new HashMap();
		Map  map1 = new HashMap();
		map.put("result", "1");
		map1.put("errcode", "11006");
		map1.put("errmsg", message);
		map.put("data", map1);
		try {
			return  mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
