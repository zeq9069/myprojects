package cn.ncss.jym.messagebox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.RecordService;
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
	
	@Autowired
	private RecordService recordService;

	@RequestMapping(value = { "main", "" }, method = RequestMethod.GET)
	public ModelAndView main(ModelAndView model) {
		model.setViewName("/home/main");
		model.addObject("systemInfo", statisticService.getAllInfo());
		return model;
	}

	@RequestMapping(value = "receives/notlookover", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView notLookover(ModelAndView model) {
		List<Announcement> list=announcementService.getAnnounsByNot();
		Map<String,List<Announcement>> resultMap=new HashMap<String, List<Announcement>>();
		resultMap.put("notlookover", list);
		
		model.setViewName("/home/notlookover");
		model.addObject("resultMap", resultMap);
		model.addObject("count",list.size());
		return model;
	}
	
	@RequestMapping(value = "receives/lookover", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView lookover(ModelAndView model) {
		model.setViewName("/home/lookover");
		return model;
	}
	
	


	@RequestMapping(value = "send", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView send(ModelAndView model) {
		model.setViewName("/home/send");
		return model;
	}

	@RequestMapping(value = "announs", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView announs(ModelAndView model) {
		model.setViewName("/home/announs");
		return model;
	}
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
