package com.zhiweifenxi.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

 
/**
 * freemarker controller
 * @author kyrin
 *
 */
@Controller
@RequestMapping(value="freemarker")
public class FreeMarkerController {
	
	 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="test1")
	public String resolver2(ModelMap map){
		Map user=new HashMap();
		user.put("name", "kyrin");
		user.put("age", 32);
		user.put("birthday", "1992-6");
		map.put("user", user);
		
		return "test1.ftl";
	}
}
