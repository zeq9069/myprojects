package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * *************************
 * 
 * 		公告查看记录持久层实现
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
@Repository
public class RecordDaoImpl implements RecordDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean create(Record record) {
		this.getSession().save(record);
		return true;
	}

	@Override
	public boolean delete(Record record) {
		this.getSession().delete(record);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getListByUId(UserInfo userInfo,int currentIndex,int pageSize) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", userInfo));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Record> getListByUId(UserInfo userInfo){
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", userInfo));
		return crit.list();
	}
	
	@Override
	public long getCount(UserInfo userInfo) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", userInfo));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.countDistinct("announ"));
		crit.setProjection(pro);
		return (long) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getListByAnnounId(int announ_id,int currentIndex,int pageSize) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("announ", announ_id));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	

	@Override
	public int getCount(int announ_id) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("announ", announ_id));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.countDistinct("user"));
		crit.setProjection(pro);
		return (int) crit.uniqueResult();
	}
	
	@Override
	public int getAnnounByViews(int announ_id) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("announ", announ_id));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (int) crit.uniqueResult();
	}

	@Override
	public boolean isExists(UserInfo user, Announcement announ) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", user));
		crit.add(Restrictions.eq("announ", announ));
		return crit.uniqueResult() == null ? false : true;
	}

}
