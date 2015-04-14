package cn.ncss.jym.messagebox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.ncss.jym.messagebox.pojo.AnnounType;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.service.AnnounTypeService;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.RecordService;
import cn.ncss.jym.messagebox.service.StatisticService;
import cn.ncss.jym.messagebox.service.UserInfoService;

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
	@Autowired
	private RecordService recordService;
	@Autowired
	private AnnounTypeService announTypeService;
	

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public Map<String,Long> getInfo() {
		return statisticService.getAllInfo();
	}
	

	@RequestMapping(value = "receives/notlookover/count",method=RequestMethod.GET)
	public int getCount(String group) {
		List<Announcement> list=announcementService.getAnnounsByNot();
		return list==null?0:list.size();
	}
	

	//查询所有已经看过的公告
	@RequestMapping(value = "receives/lookover/list",method=RequestMethod.GET)
	public List<Announcement> lookover(int currentIndex,int pageSize) {
		//TODO
		return recordService.getListByUId(currentIndex, pageSize);
	}
	
		//查询所有已经看过的公告
		@RequestMapping(value = "receives/lookover/count",method=RequestMethod.GET)
		public long lookoverCount() {
			return recordService.getCountByUser();
		}

	

	@RequestMapping(value="announs/list",method=RequestMethod.GET)
	public List<Announcement> announsInfoList(int currentIndex,int pageSize){
		return announcementService.getListByUser(currentIndex, pageSize);
	} 
	
	@RequestMapping(value="announs/count",method=RequestMethod.GET)
	public long announsCount(String online){
		return announcementService.getCountByUser();
	} 
	
	@RequestMapping(value="announs/type",method=RequestMethod.POST)
	public void addAnnouncementType(String type){
		announTypeService.create(type);
	}
	
	//查询公告类型list，每次最多10条
	@RequestMapping(value="announs/type/list")
	public List<AnnounType> announTypeList(String key){
		return announTypeService.announTypeList(key);
	}
	
	
	//[{"id":"101","text":"郑州大学"},{"id":"11","text":"北京大学"}]
	//每次十条
	@RequestMapping(value="school/list",method=RequestMethod.GET)
	public List<Map<String,String>> searchSchool(String q){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(int i=0;i<10;i++){
			Map<String, String> map=new HashMap<String, String>();
			map.put("id", q.hashCode()+""+i);
			map.put("text", q+""+i+"大学");
			list.add(map);
		}
		return list;
	}
	
	
	
	
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
