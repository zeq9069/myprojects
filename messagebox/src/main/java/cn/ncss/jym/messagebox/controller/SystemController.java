package cn.ncss.jym.messagebox.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.service.SystemService;
import cn.ncss.jym.messagebox.system.pojo.SystemInfo;

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
	
	@RequestMapping(value="info",method=RequestMethod.GET)
	public SystemInfo getInfo(){
		return systemService.getAllInfo();
	}
	
	@RequestMapping(value="groups/add",method=RequestMethod.POST)
	public Map<String,String> add(Group group){
		return systemService.addGroup(group);
	}
	
	@RequestMapping(value="groups/delete",method=RequestMethod.POST)
	public Map<String,String> delete(String name){
		return systemService.deletGroup(name);
	}
}
