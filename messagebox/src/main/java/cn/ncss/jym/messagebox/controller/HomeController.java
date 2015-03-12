package cn.ncss.jym.messagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.ncss.jym.messagebox.service.SystemService;

/**
 * ***********************
 * 
 *   主页面视图控制
 *
 * ***********************
 * @author kyrin [2015年3月12日]
 *
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value={"main",""},method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		model.setViewName("/home/main");
		model.addObject("systemInfo",systemService.getAllInfo());
		return model;
	}
	
	
}
