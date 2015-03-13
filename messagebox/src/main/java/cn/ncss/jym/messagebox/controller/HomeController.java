package cn.ncss.jym.messagebox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.GroupService;
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
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value={"main",""},method=RequestMethod.GET)
	public ModelAndView main(ModelAndView model){
		model.setViewName("/home/main");
		model.addObject("systemInfo",systemService.getAllInfo());
		return model;
	}
	
	@RequestMapping(value="groups",method=RequestMethod.GET)
	public ModelAndView groups(ModelAndView model){
		Map<String,String> map=systemService.getGroups();
		model.setViewName("/home/groups");
		model.addObject("groups",map);
		return model;
	}
	
	@RequestMapping(value="users",method=RequestMethod.GET)
	@ResponseBody
	public List<UserInfo> users(ModelAndView model){
//		model.setViewName("/home/users");
//		return model;
		return systemService.getUsers();
	}
	
}
