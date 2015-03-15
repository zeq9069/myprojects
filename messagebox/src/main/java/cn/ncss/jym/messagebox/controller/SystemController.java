package cn.ncss.jym.messagebox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.Relation;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.GroupService;
import cn.ncss.jym.messagebox.service.SystemService;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.system.pojo.SystemInfo;
import cn.ncss.jym.messagebox.utils.Constant;
import cn.ncss.jym.messagebox.utils.StringUtil;

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
	@Autowired
	private UserInfoService userInfoService;
	
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
	
	@RequestMapping(value="announs/online/update")
	public Map<String,String> updateOnline(String announ_id,String online){
		Map<String,String> resultMap=new HashMap<String, String>();
		if(!StringUtil.hasText(online) || !StringUtil.hasText(announ_id)){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE,"参数异常");
			return resultMap;
		}
		return systemService.updateOnline(Integer.parseInt(announ_id), online);
	}
	
	
	
	@RequestMapping(value="users/group/add",method=RequestMethod.POST)
	public Map<String,String> addGroupforUser(HttpServletRequest request){
		String[] names=request.getParameterValues("groupName");
		String u_id=request.getParameter("u_id");
		Map<String,String> resultMap=new HashMap<String, String>();
		if(names==null || u_id==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "参数为空");
			return resultMap;
		}
		UserInfo userInfo=userInfoService.getById(u_id);
		if(userInfo==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "该用户不存在");
			return resultMap;
		}
		
		List<Relation> relationList=new ArrayList<Relation>();
		
		for(String name:names){
			Group group=groupService.get(name);
			Relation relation=new Relation();
			
			relation.setGroup(group);
			relation.setUserInfo(userInfo);
			
			relationList.add(relation);
		}
		return userInfoService.addRelations(relationList);
	}
	
	@RequestMapping(value="users/group/delete",method=RequestMethod.POST)
	public Map<String,String> deleteGroupforUser(String groupName,String u_id){
		
		Map<String,String> resultMap=new HashMap<String, String>();
		if(groupName==null || u_id==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "参数为空");
			return resultMap;
		}
		UserInfo userInfo=userInfoService.getById(u_id);
		if(userInfo==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "该用户不存在");
			return resultMap;
		}
		return userInfoService.deleteRelation(u_id,groupName);
	}
	
	@RequestMapping(value="users/record/add",method=RequestMethod.GET)
	public Map<String,String> addRecord(String u_id,int announ_id){
		Map<String,String> resultMap=new HashMap<String, String>();
		if(!StringUtil.hasText(u_id) || !StringUtil.hasText(""+announ_id)){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "参数异常");
			return resultMap;
		}
		return systemService.addRecord(u_id, announ_id);
	}
	
	@RequestMapping(value="users/get", method=RequestMethod.GET)
	public Map<String,Object> getUser(String u_id){
		UserInfo u=systemService.getUser(u_id);
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		Set<Relation> rset=u.getRelations();
		Set<Record> recordset=u.getRecords();
		Map<String,Object> map=new HashMap<String, Object>();
		
		
		//已经查看的公告
		for(Record rr:recordset){
			list2.add(rr.getAnnoun().getTitle());
		}
		
		
		
		//全部可以查看的公告
		for(Relation r:rset){
			Set<Group_announ> gaset=r.getGroup().getGroup_announs();
			for(Group_announ gg:gaset){
				list1.add(gg.getAnnoun().getTitle());
			}
		}
		
		//还没有读取的公告
		list1.removeAll(list2);
		
		
		
		
		map.put("num1", list1);
		map.put("num2", list2);
		
		return map;
	}
}
