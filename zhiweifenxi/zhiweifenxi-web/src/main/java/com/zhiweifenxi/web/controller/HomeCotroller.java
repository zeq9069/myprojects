package com.zhiweifenxi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author kyrin
 */
@Controller
@RequestMapping(value="/")
public class HomeCotroller {
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
 		return "index";
	}
	
}
