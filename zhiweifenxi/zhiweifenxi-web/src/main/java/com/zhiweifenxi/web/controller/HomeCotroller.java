package com.zhiweifenxi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author kyrin
 */
@Controller
@RequestMapping(value="/page")
public class HomeCotroller {
	
	@RequestMapping(value="index1",method=RequestMethod.GET)
	public String index(){
 		return "index";
	}
	@RequestMapping(value="index2",method=RequestMethod.GET)
	public String index2(){
 		return "index2";
	}
	@RequestMapping(value="index3",method=RequestMethod.GET)
	public String index3(){
 		return "index3";
	}
	@RequestMapping(value="index4",method=RequestMethod.GET)
	public String index4(){
 		return "index4";
	}
	@RequestMapping(value="index5",method=RequestMethod.GET)
	public String index5(){
 		return "index5";
	}
	
	
}
