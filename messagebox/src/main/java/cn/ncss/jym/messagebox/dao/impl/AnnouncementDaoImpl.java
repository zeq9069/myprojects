package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group_announ;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.utils.StringUtil;

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
	public int add(Announcement announ) {
		return (int) this.getSession().save(announ);
	}

	@Override
	public boolean addGroup_announ(List<Group_announ> lists) {
		Session session = this.getSession();
		for (Group_announ ga : lists) {
			session.save(ga);
		}
		return true;
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
	public boolean updateOnline(int announ_id, String online) {
		String hql = "update Announcement set online=:online where id=:id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("online", online);
		query.setParameter("id", announ_id);
		int res = query.executeUpdate();
		if (res > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Announcement get(int id) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getByStatus(String status) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		if (StringUtil.hasText(status)) {
			crit.add(Restrictions.eq("online", status));
		}
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group_announ> getGroups(Announcement announ) {
		Criteria crit = this.getSession().createCriteria(Group_announ.class);
		crit.add(Restrictions.eq("announ", announ));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group_announ> getGroupsOfAnnoun(Announcement announ) {
		Criteria crit = this.getSession().createCriteria(Group_announ.class);
		crit.add(Restrictions.eq("announ", announ));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getAnnounByViews(Announcement announ) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("announ", announ));
		return crit.list();
	}

}
