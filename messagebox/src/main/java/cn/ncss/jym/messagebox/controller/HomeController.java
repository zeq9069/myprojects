package cn.ncss.jym.messagebox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Group_announ;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.GroupService;
import cn.ncss.jym.messagebox.service.SystemService;
import cn.ncss.jym.messagebox.utils.Constant;

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
	public ModelAndView users(ModelAndView model){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<UserInfo> userList=systemService.getUsers(1,Constant.HTTP_PAGESIZE);
		model.setViewName("/home/users");
		resultMap.put("users", userList);
		resultMap.put("groups", systemService.getGroups());
		resultMap.put(Constant.HTTP_COUNT_NAME,systemService.getCount());
		resultMap.put(Constant.HTTP_PAGESIZE_NAME,Constant.HTTP_PAGESIZE);
		model.addObject("resultMap",resultMap);
		return model;
	}
	
	@RequestMapping(value="users/group",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView usersByGroup(String name,ModelAndView model){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<UserInfo> userList=systemService.getUsersByGroup(1, Constant.HTTP_PAGESIZE, name);
		System.out.println(userList.size());
		model.setViewName("/home/users");
		resultMap.put("users", userList);
		resultMap.put("groups", systemService.getGroups());
		resultMap.put(Constant.HTTP_COUNT_NAME, systemService.getCountByGroup(name));
		resultMap.put(Constant.HTTP_PAGESIZE_NAME,Constant.HTTP_PAGESIZE);
		model.addObject("resultMap",resultMap);
		return model;
	}
	
	
	@RequestMapping(value="send",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView send(ModelAndView model){
		model.setViewName("/home/send");
		model.addObject("groups", systemService.getGroups());
		return model;
	}
	@RequestMapping(value="announs",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView announs(ModelAndView model){
		model.setViewName("/home/announs");
		model.addObject("announs_online", systemService.getAnnouns(Constant.ANNOUN_ONLINE));
		model.addObject("announs_offline", systemService.getAnnouns(Constant.ANNOUN_OFFLINE));
		return model;
	}
	

	@RequestMapping(value="announs/look",method=RequestMethod.GET)
	public ModelAndView lookAnnoun(int announ_id,ModelAndView model){
 		Announcement announ=systemService.getAnnoun(announ_id);
 		model.setViewName("/home/announ_info");
 		Map<String,String> groupMap=new HashMap<String, String>();
		if(announ!=null){
			announ.setRecords(null);
			Set<Group_announ> set=announ.getGroup_announs();
			for(Group_announ ga:set){
				Group gg=ga.getGroup();
				groupMap.put(gg.getId()+"", gg.getName());
			}
		}
		
		model.addObject("groups", groupMap);
		model.addObject("announ", announ);
		
		return model;
	}
	
	
}
