package cn.ncss.jym.messagebox.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.utils.Constant;

/**
 * ***********************
 * 
 *   用户登录controller
 *
 * ***********************
 * @author kyrin [2015年3月10日]
 *
 */
@RestController
@RequestMapping(value="/home")
public class LoginController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(String name,String password,HttpServletRequest request){
		if("kyrin".equals(name) && "123".equals(password)){
			HttpSession session=request.getSession();
			session.setAttribute(Constant.USER_NAME,name);
			return "home";
		}
		return "login";
	}
	
	@RequestMapping(value="users",method=RequestMethod.GET)
	public List<UserInfo> users(){
		return userInfoService.getList();
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public ModelAndView get(ModelAndView model){
		model.setViewName("list");
		return model;
	}
	
	
}
