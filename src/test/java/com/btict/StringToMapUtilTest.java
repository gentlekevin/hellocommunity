package com.btict;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.btict.rest.StringToMapUtil;

public class StringToMapUtilTest {

	@Test
	public void test() {
		String json = "{\"status\":{\"succeed\":0,\"error_code\":11,\"error_desc\":\"\u7528\u6237\u540d\u6216email\u5df2\u4f7f\u7528\"}}";
		
		Map <String,Map>map = StringToMapUtil.StringMapFromJSON(json);
		
		Map<String,String> stauts =  map.get("status");
	
		String s = stauts.get("error_desc");
		
		System.out.println(s);
		
	}

}
