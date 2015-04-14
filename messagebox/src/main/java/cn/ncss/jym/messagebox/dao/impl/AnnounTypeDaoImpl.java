package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.AnnounTypeDao;
import cn.ncss.jym.messagebox.pojo.AnnounType;

/**
 * ***********************
 *   公告类型
 *
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月14日]
 *
 */
@Repository
public class AnnounTypeDaoImpl implements AnnounTypeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void create(AnnounType type) {
		this.getSession().save(type);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AnnounType> queryList(String key){
		Criteria cri=this.getSession().createCriteria(AnnounType.class);
		cri.add(Restrictions.like("type", key,MatchMode.ANYWHERE));
		cri.setMaxResults(10);
		return cri.list();
	}
	
	@Override
	public AnnounType find(String type){
		Criteria cri=this.getSession().createCriteria(AnnounType.class);
		cri.add(Restrictions.eq("type", type));
		return (AnnounType) cri.uniqueResult();
	}

	
}
