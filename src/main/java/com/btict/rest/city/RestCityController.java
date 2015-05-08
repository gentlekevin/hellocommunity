package com.btict.rest.city;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;
import com.btict.entity.City;
import com.btict.service.CityService;



@RestController
@RequestMapping(value="/rest/city")
public class RestCityController {
  
	 @Autowired
    public CityService cityService;
	 
	 @RequestMapping( value="/findAll", method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	public Map findAllCities(){
		
	    List<City> list = cityService.findAllCity();
		Map map = new HashMap();
		map.put("result", "0");
		List data  = new ArrayList<>(); 
		for(City city :list){
			data.add(new RestCity(String.valueOf(city.getId()), city.getName()));
		}
		map.put("data",data);
		return map;
		 
	}
	 
	 
	 
	 
	 public class RestCity{
			private String cityId;
			private String cityName;
			public RestCity(String cityId, String cityName) {
				super();
				this.cityId = cityId;
				this.cityName = cityName;
			}
			public String getCityId() {
				return cityId;
			}
			public void setCityId(String cityId) {
				this.cityId = cityId;
			}
			public String getCityName() {
				return cityName;
			}
			public void setCityName(String cityName) {
				this.cityName = cityName;
			}
	}
}
