package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.GroupDao;
import cn.ncss.jym.messagebox.pojo.Group;

/**
 * *************************
 * 
 *  用户群组持久层实现
 * 
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
@Repository
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSessioin() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void add(Group group) {
		this.getSessioin().save(group);
	}

	@Override
	public void delete(Group group) {
		this.getSessioin().delete(group);
	}

	@Override
	public void update(Group group) {
		this.getSessioin().update(group);
	}

	@Override
	public Group get(String name) {
		Criteria crit = this.getSessioin().createCriteria(Group.class);
		crit.add(Restrictions.eq("groupName", name));
		return (Group) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getList() {
		Criteria crit = this.getSessioin().createCriteria(Group.class);
		return crit.list();
	}

}
