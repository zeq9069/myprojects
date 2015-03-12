package cn.ncss.jym.messagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ncss.jym.messagebox.service.SystemService;
import cn.ncss.jym.messagebox.system.pojo.SystemInfo;

/**
 * ***********************
 * 
 *  meesageBox系统服务RESTFUL接口
 *
 * ***********************
 * @author kyrin [2015年3月12日]
 *
 */
@RestController
@RequestMapping(value="/system")
public class SystemController {
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value="info")
	public SystemInfo getInfo(){
		return systemService.getAllInfo();
	}
}
