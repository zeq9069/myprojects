package cn.ncss.jym.messagebox.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Group_announ;
import cn.ncss.jym.messagebox.service.GroupService;
import cn.ncss.jym.messagebox.service.SystemService;
import cn.ncss.jym.messagebox.system.pojo.SystemInfo;
import cn.ncss.jym.messagebox.utils.Constant;

/**
 * **********************************
 * 
 *  meesageBox对外开放系统服务RESTFUL接口
 *  
 *  不需要跳转页面
 *
 * **********************************
 * @author kyrin [2015年3月12日]
 *
 */
@RestController
@RequestMapping(value="/system")
public class SystemController {
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value="info",method=RequestMethod.GET)
	public SystemInfo getInfo(){
		return systemService.getAllInfo();
	}
	
	@RequestMapping(value="groups/add",method=RequestMethod.POST)
	public Map<String,String> addGroup(Group group){
		return systemService.addGroup(group);
	}
	
	@RequestMapping(value="groups/delete",method=RequestMethod.POST)
	public Map<String,String> deleteGroup(String name){
		return systemService.deletGroup(name);
	}
	//发布公告
	@RequestMapping(value="announs/add",method=RequestMethod.POST)
	public Map<String,String> publisAnnoun(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> resultMap=new HashMap<String, String>();

		if(request==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "请求出错");
			return resultMap;
		}
		
		/******************获取参数************************/
		String title=request.getParameter("title");
		String[] group=request.getParameterValues("group");
		String type=request.getParameter("type");
		String content=request.getParameter("content");
		String online=request.getParameter("online");
		String publisher=request.getParameter("publisher");
		
		if(title==null || group==null ||   type==null || content==null || online==null || publisher==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "请求参数缺失");
			return resultMap;
		}
		
		System.out.println("标题："+title+"\n 群组："+group.length+"\n type:"+type+"\n content:"+content+"\n online:"+online);
		/******************处理级联***********************/
		Announcement announ=new Announcement();
		announ.setContent(content);
		announ.setOnline(online);
		announ.setPublisher(publisher);
		announ.setTitle(title);
		announ.setType(type);
		Set<Group_announ> set=announ.getGroup_announs();
		for(String str:group){
			Group_announ f=new Group_announ(); 
			f.setGroup(groupService.get(str));
			f.setAnnoun(announ);
			set.add(f);
		}
		announ.setGroup_announs(set);
		return systemService.publishAnnoun(announ);
	}
	
	
	
	
	
	
	
}
