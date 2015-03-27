package cn.ncss.jym.messagebox.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.ncss.jym.messagebox.service.UserInfoService;


/**
 * ***********************
 * 
 *   用户登录controller
 *
 * ***********************
 * @author kyrin [2015年3月10日]
 *
 */
@Controller
@RequestMapping(value="/")
public class LoginController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value={"","index","index.html","index.jsp"},method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model,HttpServletRequest request){
		//request.getSession().setAttribute("username", "kyrin");
		model.setViewName("index");
		if(request.getSession().getAttribute("username")!=null){
		model.addObject("username", request.getSession().getAttribute("username").toString());
		}
		return model;
	}
	
	@RequestMapping(value={"login.html"},method=RequestMethod.GET)
	public String login(ModelAndView model,HttpServletRequest request){
		//request.getSession().setAttribute("username", "kyrin");
		model.setViewName("index");
		if(request.getSession().getAttribute("username")!=null){
		model.addObject("username", request.getSession().getAttribute("username").toString());
		}
		return "index";
	}
	

	
}
