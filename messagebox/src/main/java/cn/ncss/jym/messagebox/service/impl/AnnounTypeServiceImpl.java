package cn.ncss.jym.messagebox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnounTypeDao;
import cn.ncss.jym.messagebox.pojo.AnnounType;
import cn.ncss.jym.messagebox.service.AnnounTypeService;

/**
 * ***********************
 * 
 *   公告类型服务
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月14日]
 *
 */
@Service
public class AnnounTypeServiceImpl implements AnnounTypeService{

	@Autowired
	private AnnounTypeDao announTypeDao;

	@Transactional(readOnly=false)
	@Override
	public void create(String type) {
		if(!isExist(type)){
			announTypeDao.create(new AnnounType(type));
		}
	}
	

	@Transactional(readOnly=true)
	@Override
	public List<AnnounType> announTypeList(String key) {
		return announTypeDao.queryList(key);
	}
	
	
	@Transactional(readOnly=true)
	public boolean isExist(String type){
		return announTypeDao.find(type)==null?false:true;
	}
	
}
