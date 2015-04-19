package cn.ncss.jym.messagebox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.ncss.jym.messagebox.pojo.AnnounType;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.service.AnnounTypeService;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.RecordService;
import cn.ncss.jym.messagebox.service.StatisticService;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.system.pojo.TargetSchool;
import cn.ncss.jym.messagebox.utils.Constant;

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
	public Long getCount() {
		return recordService.getCountByNotView();
	}
	

	//查询所有已经看过的公告
	@RequestMapping(value = "receives/lookover/list",method=RequestMethod.GET)
	public List<Announcement> lookover(int currentIndex,int pageSize) {
		//TODO
		return recordService.getListByView(currentIndex, pageSize);
	}
	
	//查询所有已经看过的公告
	@RequestMapping(value = "receives/lookover/count",method=RequestMethod.GET)
	public long lookoverCount() {
		return recordService.getCountByView();
	}

	

	@RequestMapping(value="announs/list",method=RequestMethod.GET)
	public List<Announcement> announsInfoList(int currentIndex,int pageSize){
		return announcementService.getListByUser(currentIndex, pageSize);
	} 
	
	@RequestMapping(value="announs/count",method=RequestMethod.GET)
	public long announsCount(){
		return announcementService.getCount();
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
			map.put("id", q.hashCode()+""+i);//院校代码
			map.put("text", q+""+i+"大学"+"[fxmc"+i+"]");  //院校名称
			map.put("fxmc", q+"fxmc"+i);    //分校名称
			list.add(map);
		}
		return list;
	}
	

	//发布公告
	@SuppressWarnings({  "unused" })
	@RequestMapping(value = "announs", method = RequestMethod.POST)
	public Map<String, String> publisAnnoun(HttpServletRequest request) {
		
		Map<String, String> resultMap = new HashMap<String, String>();
		Announcement announcement=new Announcement();
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String targetProvinceCode=request.getParameter("targetProvinceCode");
		String jsontargetSchools= request.getParameter("targetSchools");
		String targetYxlx= request.getParameter("targetYxlx");
		String type=request.getParameter("type");
		JSONArray array=JSON.parseArray(jsontargetSchools);
		
		announcement.setTitle(title);
		announcement.setContent(content);
		announcement.setTargetProvinceCode(targetProvinceCode);
		announcement.setTargetYxlx(targetYxlx);
		announcement.setType(type);
		
		List<TargetSchool> targetSchoolList=new ArrayList<TargetSchool>();
		
		for(Object obj:array){
			JSONObject json=(JSONObject) JSON.toJSON(obj);
			TargetSchool school=JSON.toJavaObject(json, TargetSchool.class);
			targetSchoolList.add(school);
		}
		
		if (announcement == null) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "发布出错");
			return resultMap;
		}
		
		
		try {
			return announcementService.create(announcement,targetSchoolList);
		} catch (InterruptedException e) {
			e.printStackTrace();
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "发布出错");
			return resultMap;
		} catch (ExecutionException e) {
			e.printStackTrace();
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "发布出错");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "发布出错");
			return resultMap;
		}
		
	}
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

	
}
