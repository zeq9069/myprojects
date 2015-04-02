package cn.ncss.jym.messagebox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.StatisticService;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.utils.Constant;
import cn.ncss.jym.messagebox.utils.StringUtil;

/**
 * **********************************
 * 
 *  meesageBox对外开放系统服务RESTFUL接口
 *  
 *  不需要跳转页面
 *  
 *   GET:获取资源
 *  POST:添加资源
 *   PUT:更新资源
 *DELETE:删除资源
 *
 * **********************************
 * @author kyrin [2015年3月12日]
 *
 */
@RestController
@RequestMapping(value = "/system")
public class SystemController {

	@Autowired
	private StatisticService statisticService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AnnouncementService announcementService;
	
	

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public Map<String,Long> getInfo() {
		return statisticService.getAllInfo();
	}

//	@RequestMapping(value = "groups", method = RequestMethod.POST)
//	public Map<String, String> addGroup(Group group) {
//		return groupService.add(group);
//	}
//
//	@RequestMapping(value = "groups/{group_id}", method = RequestMethod.DELETE)
//	public Map<String, String> deleteGroup(@PathVariable("group_id") int group_id) {
//		return groupService.delete(group_id);
//	}
//	
//	
//	@RequestMapping(value="announs",method=RequestMethod.GET)
//	public List<AnnounsInfo> announsInfoList(int currentIndex,int pageSize,String online){
//		if(online==null || (!online.equals(Constant.ANNOUN_OFFLINE) && !online.equals(Constant.ANNOUN_ONLINE))){
//			return null;
//		}
//		List<AnnounsInfo> resultlist = new ArrayList<AnnounsInfo>();
//		List<Announcement> announs = announcementService.getListByOnline(currentIndex,pageSize,online);
//
//		for (Announcement announ : announs) {
//			AnnounsInfo aInfo = new AnnounsInfo();
//			aInfo.setAnnoun(announ);
//			aInfo.setGroups(announcementService.getGroupsOfAnnoun(announ));
//			List<UserInfo> list = announcementService.getAnnounByViews(announ);
//			aInfo.setViews(list.size());
//			resultlist.add(aInfo);
//		}
// 		return resultlist;
//	} 
//	
//	@RequestMapping(value="announs/count",method=RequestMethod.GET)
//	public int announsCount(String online){
//		if(online==null || (!online.equals(Constant.ANNOUN_OFFLINE) && !online.equals(Constant.ANNOUN_ONLINE))){
//			return 0;
//		}
//		List<Announcement> announs = announcementService.getListByOnline(online);
// 		return announs==null?0:announs.size();
//	} 
//	
//
//	//发布公告
//	@RequestMapping(value = "announs", method = RequestMethod.POST)
//	public Map<String, String> publisAnnoun(HttpServletRequest request, HttpServletResponse response) {
//		Map<String, String> resultMap = new HashMap<String, String>();
//
//		if (request == null) {
//			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
//			resultMap.put(Constant.HTTP_MESSAGE, "请求出错");
//			return resultMap;
//		}
//
//		/******************获取参数************************/
//		String title = request.getParameter("title");
//		String[] group = request.getParameterValues("group");
//		String type = request.getParameter("type");
//		String content = request.getParameter("editorValue");
//		String online = request.getParameter("online");
//		String publisher = request.getParameter("publisher");
//
//		if (!StringUtil.hasText(title) || group == null || group.length == 0 || !StringUtil.hasText(type)
//				|| !StringUtil.hasText(content) || !StringUtil.hasText(online) || !StringUtil.hasText(publisher)) {
//			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
//			resultMap.put(Constant.HTTP_MESSAGE, "请求参数缺失");
//			return resultMap;
//		}
//
//		/******************处理级联***********************/
//		Announcement announ = new Announcement();
//		announ.setContent(content);
//		announ.setOnline(online);
//		announ.setPublisher(publisher);
//		announ.setTitle(title);
//		announ.setType(type);
//
//		List<Group> groupList = new ArrayList<Group>();
//
//		for (String str : group) {
//			Group g = groupService.get(str);
//			if (g == null) {
//				resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
//				resultMap.put(Constant.HTTP_MESSAGE, "包含不存在的群组");
//				return resultMap;
//			} else {
//				groupList.add(g);
//			}
//		}
//		return announcementService.add(announ, groupList);
//	}
//	
//	@RequestMapping(value = "announs/{announ_id}",method=RequestMethod.PUT)
//	public Map<String, String> updateOnline(@PathVariable("announ_id") String announ_id,  String online) {
//		Map<String, String> resultMap = new HashMap<String, String>();
//		if (!StringUtil.hasText(online) || !StringUtil.hasText(announ_id)) {
//			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
//			resultMap.put(Constant.HTTP_MESSAGE, "参数异常");
//			return resultMap;
//		}
//		return announcementService.updateOnline(Integer.parseInt(announ_id), online);
//	}
//
//	
//	@RequestMapping(value = "relations/{u_id}", method = RequestMethod.POST)
//	public Map<String, String> addGroupforUser(@PathVariable("u_id")String u_id,HttpServletRequest request) {
//		String[] names = request.getParameterValues("groupName");
//		Map<String, String> resultMap = new HashMap<String, String>();
//		if (names == null || u_id == null) {
//			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
//			resultMap.put(Constant.HTTP_MESSAGE, "参数为空");
//			return resultMap;
//		}
//		UserInfo userInfo = userInfoService.getById(u_id);
//		if (userInfo == null) {
//			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
//			resultMap.put(Constant.HTTP_MESSAGE, "该用户不存在");
//			return resultMap;
//		}
//
//		List<Relation> relationList = new ArrayList<Relation>();
//
//		for (String name : names) {
//			Group group = groupService.get(name);
//			Relation relation = new Relation();
//
//			relation.setGroup(group);
//			relation.setUserInfo(userInfo);
//
//			relationList.add(relation);
//		}
//		return userInfoService.addRelations(relationList);
//	}
//
//	@RequestMapping(value = "users",method=RequestMethod.GET)
//	public List<UserSys> getUsers(int currentIndex, int pageSize, String group) {
//		List<UserInfo> userList=null;
//		List<UserSys> usersys=new ArrayList<UserSys>();
//		if(group==null || "all".equals(group)){
//			userList=userInfoService.getList(currentIndex, pageSize);
//		}else{
//			Group gr = groupService.get(group);
//			userList = userInfoService.getUsersByGroup(currentIndex, Constant.HTTP_PAGESIZE,gr);
//		}
//		if(userList!=null){
//			for(UserInfo user:userList){
//				UserSys u=new UserSys();
//				u.setUser(user);
//				u.setGroups(userInfoService.getGroupsByUser(user));
//				usersys.add(u);
//			}
//		}
//		return usersys;
//	}
//
//	@RequestMapping(value = "users/count",method=RequestMethod.GET)
//	public int getCount(String group) {
//		int count=0;
//		if(group==null || "all".equals(group)){
//			count=userInfoService.getCount();
//		}else{
//			Group g = groupService.get(group);
//			count = userInfoService.getCountByGroup(g);
//		}
//		return count;
//	}
//
//	@RequestMapping(value = "relations/{u_id}", method = RequestMethod.DELETE)
//	public Map<String, String> deleteGroupforUser(String groupName, @PathVariable("u_id") String u_id) {
//
//		Map<String, String> resultMap = new HashMap<String, String>();
//		if (groupName == null || u_id == null) {
//			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
//			resultMap.put(Constant.HTTP_MESSAGE, "参数为空");
//			return resultMap;
//		}
//		UserInfo userInfo = userInfoService.getById(u_id);
//		if (userInfo == null) {
//			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
//			resultMap.put(Constant.HTTP_MESSAGE, "该用户不存在");
//			return resultMap;
//		}
//		return userInfoService.deleteRelation(u_id, groupName);
//	}

	/*@RequestMapping(value = "users/record/add", method = RequestMethod.GET)
	public Map<String, String> addRecord(String u_id, int announ_id) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (!StringUtil.hasText(u_id) || !StringUtil.hasText("" + announ_id)) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "参数异常");
			return resultMap;
		}
		return systemService.addRecord(u_id, announ_id);
	}*/
	/*
		@RequestMapping(value = "users/get", method = RequestMethod.GET)
		public Map<String, Object> getUser(String u_id) {
			UserInfo u = systemService.getUser(u_id);
			List<String> list1 = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();
			Set<Relation> rset = u.getRelations();
			Set<Record> recordset = u.getRecords();
			Map<String, Object> map = new HashMap<String, Object>();

			//已经查看的公告
			for (Record rr : recordset) {
				list2.add(rr.getAnnoun().getTitle());
			}

			//全部可以查看的公告
			for (Relation r : rset) {
				Set<Group_announ> gaset = r.getGroup().getGroup_announs();
				for (Group_announ gg : gaset) {
					list1.add(gg.getAnnoun().getTitle());
				}
			}

			//还没有读取的公告
			list1.removeAll(list2);

			map.put("num1", list1);
			map.put("num2", list2);

			return map;
		}*/
}
