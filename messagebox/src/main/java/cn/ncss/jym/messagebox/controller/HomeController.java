package cn.ncss.jym.messagebox.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.StatisticService;

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
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private StatisticService statisticService;
	@Autowired
	private AnnouncementService announcementService;

	@RequestMapping(value = { "main", "" }, method = RequestMethod.GET)
	public ModelAndView main(ModelAndView model) {
		model.setViewName("/home/main");
		model.addObject("systemInfo", statisticService.getAllInfo());
		return model;
	}

//	@RequestMapping(value = "groups", method = RequestMethod.GET)
//	public ModelAndView groups(ModelAndView model) {
//		Map<String, String> map = groupService.getGroups();
//		model.setViewName("/home/groups");
//		model.addObject("groups", map);
//		return model;
//	}
//
//	@RequestMapping(value = "users", method = RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView users(ModelAndView model) {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		model.setViewName("/home/users");
//		resultMap.put("groups", groupService.getGroups());
//		model.addObject("resultMap", resultMap);
//		return model;
//	}
//
//
//	@RequestMapping(value = "send", method = RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView send(ModelAndView model) {
//		model.setViewName("/home/send");
//		model.addObject("groups", groupService.getGroups());
//		return model;
//	}
//
//	@RequestMapping(value = "announs", method = RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView announs(ModelAndView model) {
//		model.setViewName("/home/announs");
//		return model;
//	}
//	
//	@RequestMapping(value = "announs/look", method = RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView announsLook(int announ_id,ModelAndView model) {
//		model.setViewName("/home/announ_info");
//		Announcement announ=announcementService.get(announ_id);
//		AnnounsInfo announsInfo=new AnnounsInfo();
//		if(announ!=null){
//			announsInfo.setAnnoun(announ);
//			announsInfo.setGroups(announcementService.getGroupsOfAnnoun(announ));
//			announsInfo.setViews(announcementService.getAnnounByViews(announ).size());
//		}
//		model.addObject("announsInfo", announsInfo);
//		return model;
//	}
//	
	
}
