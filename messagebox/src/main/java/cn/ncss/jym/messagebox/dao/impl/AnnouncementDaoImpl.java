package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.pojo.Announcement;

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
	public void add(Announcement announ) {
		this.getSession().save(announ);
	}

	@Override
	public void delete(Announcement announ) {
		this.getSession().delete(announ);
	}

	@Override
	public void update(Announcement announ) {
		this.getSession().update(announ);
	}

	@Override
	public Announcement get(String id) {
		return (Announcement) this.getSession().get(Announcement.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getListByType(String type) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("type", type));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getListByUser(String publisher) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("publisher", publisher));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getListByType(String type, boolean online) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("type", type));
		crit.add(Restrictions.eq("online", online + ""));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getListByUser(String publisher, boolean online) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("publisher", publisher));
		crit.add(Restrictions.eq("online", online + ""));
		return crit.list();
	}
}