package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.pojo.Record;

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
	public void add(Record record) {
		this.add(record);
	}

	@Override
	public void delete(Record record) {
		this.getSession().delete(record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getListByUId(String u_id) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("user", u_id));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getListByAnnounId(String announ_id) {
		Criteria crit = this.getSession().createCriteria(Record.class);
		crit.add(Restrictions.eq("announ", announ_id));
		return crit.list();
	}
}
