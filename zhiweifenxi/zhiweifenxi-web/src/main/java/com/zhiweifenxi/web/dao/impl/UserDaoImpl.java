package com.zhiweifenxi.web.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhiweifenxi.web.dao.UserDao;
import com.zhiweifenxi.web.pojo.User;

/**
 * 
 * @author kyrin
 *
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

	public User get(String name) {
		Session session=this.getSession();
		Criteria cri=session.createCriteria(User.class);
		cri.add(Restrictions.eq("name", name));
		return (User) cri.uniqueResult();
 	}
	

}
