package com.zhiweifenxi.web.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhiweifenxi.web.dao.UserDao;
import com.zhiweifenxi.web.pojo.User;

/**
 * 用户持久成实现
 * @author kyrin
 */
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
			return sessionFactory.getCurrentSession();
	}
	
	public boolean add(User user) {
		Session session=this.getSession();
		session.save(user);
		return true;
	}

	public User getByName(String name) {
		Session session=this.getSession();
		Criteria cri=session.createCriteria(User.class);
		cri.add(Restrictions.eq("name", name));
		return (User) cri.uniqueResult();
 	}

	public boolean delete(User user) {
		Session session=this.getSession();
		session.delete(user);
		return true;
	}

	public boolean delById(String id) {
		String hql="delete from User  where id=:id";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("id", id);
		int res=query.executeUpdate();
		if(res==0){
			return false;
		}
		return true;
	}

	public boolean delByName(String name) {
		String hql="delete from User  where name=:name";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("name", name);
		int res=query.executeUpdate();
		if(res==0){
			return false;
		}
		return true;
	}

	public boolean update(User user) {
		Session session=this.getSession();
		session.update(user);
		return true;
	}

	public User getById(String id) {
		Session session=this.getSession();
		User user=(User) session.get(User.class, id);
		return user;
	}
}
