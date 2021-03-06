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
		try{
		this.getSession().save(record);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Record record) {
		try{
			this.getSession().delete(record);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Record getRecord(Announcement announ,UserInfo userInfo){
		Criteria cri=this.getSession().createCriteria(Record.class);
		cri.add(Restrictions.eq("user", userInfo));
		cri.add(Restrictions.eq("announ", announ));
		return (Record) cri.uniqueResult();
	}
	

	@Override
	public boolean updateStatus(Record record) {
		try{
			this.getSession().update(record);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getListByStatus(UserInfo userInfo, int status) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", userInfo));
		crit.add(Restrictions.eq("status", status));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getListByStatus(UserInfo userInfo, int status,
			int currentIndex, int pageSize) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", userInfo));
		crit.add(Restrictions.eq("status", status));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}

	@Override
	public long getCountByStatus(UserInfo userInfo,int status) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", userInfo));
		crit.add(Restrictions.eq("status", status));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (long) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getList(UserInfo userInfo,
			int currentIndex, int pageSize) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", userInfo));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}

	@Override
	public long getCount(UserInfo userInfo) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", userInfo));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (long) crit.uniqueResult();
	}
	
	@Override
	public boolean isExists(UserInfo user, Announcement announ) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", user));
		crit.add(Restrictions.eq("announ", announ));
		return crit.uniqueResult() == null ? false : true;
	}

}
