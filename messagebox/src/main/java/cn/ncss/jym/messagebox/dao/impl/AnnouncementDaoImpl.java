package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * *************************
 * 		公告持久层实现
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public int create(Announcement announ) {
		return (int) this.getSession().save(announ);
	}

	@Override
	public boolean delete(Announcement announ) {
		this.getSession().delete(announ);
		return true;
	}

	@Override
	public boolean update(Announcement announ) {
		this.getSession().update(announ);
		return true;
	}

	@Override
	public Announcement get(int id) {
		return (Announcement) this.getSession().get(Announcement.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getListByUser(UserInfo userInfo,int currentIndex,int pageSize) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("user", userInfo));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"),"id");
		pro.add(Projections.property("title"),"title");
		pro.add(Projections.property("date"),"date");
		pro.add(Projections.property("user"),"user");
		pro.add(Projections.property("type"),"type");
		crit.setProjection(pro);
		crit.setResultTransformer(Transformers.aliasToBean(Announcement.class));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	
	@Override
	public long getCountByUser(UserInfo userInfo){
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("user", userInfo));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (long) crit.uniqueResult();
	}

}
