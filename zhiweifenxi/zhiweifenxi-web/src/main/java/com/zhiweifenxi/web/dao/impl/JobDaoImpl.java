package com.zhiweifenxi.web.dao.impl;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhiweifenxi.web.dao.JobDao;
import com.zhiweifenxi.web.pojo.Job;
import com.zhiweifenxi.web.util.StringUtil;

/**
 * 职位持久层实现 
 * @author kyrin
 */
public class JobDaoImpl implements JobDao{

	@Autowired
	private SessionFactory  sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void add(Job job) {
		this.getSession().save(job);
	}

	public void add(List<Job> list) {
		if(StringUtil.isNull(list)){
			return ;
		}
		Session session=this.getSession();
		int y=0;
		for(Job job:list){
			session.save(job);
			y++;
			if(y%5==0){
				session.flush();
			}
		}	
	}

	public boolean delete(Job job) {
		Session session=this.getSession();
		session.delete(job);
		return true;
	}

	public boolean delete(String url) {
		String hql="delete from Job where url=:url";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("url", url);
		int res=query.executeUpdate();
		if(res==0){
			return false;
		}
		return true;
	}

	public boolean update(Job job) {
		this.getSession().update(job);
		return true;
	}

	public Job get(String url) {
		Criteria cri=this.getSession().createCriteria(Job.class);
		cri.add(Restrictions.eq("url", url));
		return (Job) cri.uniqueResult();
	}
	
	

}
