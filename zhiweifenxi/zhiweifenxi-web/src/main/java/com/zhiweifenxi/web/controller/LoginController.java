package com.zhiweifenxi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiweifenxi.web.service.UserService;

/**
 * 
 * @author kyrin
 */
@Controller
@RequestMapping(value="/home",produces="text/html;charset=UTF-8")
public class LoginController {

	@Autowired
	private UserService userService;
 
	@RequestMapping(value="login" ,method=RequestMethod.GET)
	@ResponseBody
	public String login(String name,String password){
		if(userService.login(name, password)){
			return " login 成功~~";
		}
 		return "login 失败 ~~~";
	}
}
