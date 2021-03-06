package cn.ncss.jym.messagebox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cn.ncss.jym.messagebox.akka.InsertActor;
import cn.ncss.jym.messagebox.akka.SendMessageActor;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.RecordService;
import cn.ncss.jym.messagebox.service.StatisticService;
import cn.ncss.jym.messagebox.service.UserInfoService;

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
	
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = { "main", "" }, method = RequestMethod.GET)
	public ModelAndView main(ModelAndView model,HttpSession session) {
//		ActorSystem system=ActorSystem.create("mySystem");
//		ActorRef ref=system.actorOf(Props.create(SendMessageActor.class,WebApplicationContextUtils.getWebApplicationContext(session.getServletContext())),"sendMessage");
//		ref.tell("send", ref);
		ActorSystem system=ActorSystem.create("mySystem");
		ActorRef ref=system.actorOf(Props.create(InsertActor.class,WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()),session),"insertActor");
		ref.tell("insert", ref);
		system.shutdown();
		model.setViewName("/home/main");
		model.addObject("systemInfo", statisticService.getAllInfo());
		model.addObject("notlook_count",recordService.getCountByNotView());
		return model;
	}

	@RequestMapping(value = "receives/notlookover", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView notLookover(ModelAndView model) {
		
		
		List<Announcement> list=recordService.getListByNotView();
		Map<String,List<Announcement>> resultMap=new HashMap<String, List<Announcement>>();
		resultMap.put("notlookover", list);
		
		model.setViewName("/home/notlookover");
		model.addObject("resultMap", resultMap);
		model.addObject("count",list.size());
		model.addObject("notlook_count",recordService.getCountByNotView());
		return model;
	}
	
	@RequestMapping(value = "receives/lookover", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView lookover(ModelAndView model) {
		model.setViewName("/home/lookover");
		model.addObject("notlook_count",recordService.getCountByNotView());
		return model;
	}
	
	


	@RequestMapping(value = "send", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView send(ModelAndView model) {
		model.setViewName("/home/send");
		model.addObject("notlook_count",recordService.getCountByNotView());
		return model;
	}

	@RequestMapping(value = "announs", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView announs(ModelAndView model) {
		model.setViewName("/home/announs");
		model.addObject("notlook_count",recordService.getCountByNotView());
		return model;
	}
	
	@RequestMapping(value = "announs/look", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView announsLook(int announ_id,ModelAndView model) {
		model.addObject("notlook_count",recordService.getCountByNotView());
		
		model.setViewName("/home/announ_info");
		Announcement announ=announcementService.get(announ_id);
		UserInfo userInfo=new UserInfo();
		Record record=new Record();
		record.setAnnoun(announ);
		userInfo.setId("123");
		record.setUser(userInfo);
		recordService.updateStatus(announ_id);
		model.addObject("announsInfo", announ);
		return model;
	}
	
}
