package cn.ncss.jym.messagebox.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Relation;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.pojo.UserInfo.UserType;
import cn.ncss.jym.messagebox.utils.StringUtil;

/**
 * *************************
 * 
 * 		用户持久层接口实现层
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
@Repository
public class UserInfoDaoImpl implements UserInfoDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addOne(UserInfo userInfo) {
		this.getSession().save(userInfo);
		return true;
	}

	@Override
	public boolean deleteOne(UserInfo userInfo) {
		this.getSession().delete(userInfo);
		return true;
	}

	@Override
	public boolean update(UserInfo userInfo) {
		this.getSession().update(userInfo);
		return true;
	}

	@Override
	public UserInfo get(String areaCode, String orgCode, String orgName, String fxmc, UserType type) {
		Criteria crit = this.getSession().createCriteria(UserInfo.class);
		if (StringUtil.hasText(areaCode)) {
			crit.add(Restrictions.eq("areaCode", areaCode));
		}
		if (StringUtil.hasText(orgCode)) {
			crit.add(Restrictions.eq("orgCode", orgCode));
		}
		if (StringUtil.hasText(orgName)) {
			crit.add(Restrictions.eq("orgName", orgName));
		}
		if (StringUtil.hasText(fxmc)) {
			crit.add(Restrictions.eq("fxmc", fxmc));
		}
			crit.add(Restrictions.eq("userType", type));
		return (UserInfo) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getList(int page,int pageSize){
		Session session=this.getSession();
		Criteria crit = session.createCriteria(UserInfo.class);
		crit.setFirstResult((page-1)*pageSize);
		crit.setMaxResults(pageSize);
		List<UserInfo> list=crit.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getUsersByGroup(int page, int pageSize,Group group) {
		Session session=this.getSession();
		Criteria crit = session.createCriteria(Relation.class);
		crit.add(Restrictions.eq("group", group));
		crit.setFirstResult((page-1)*pageSize);
		crit.setMaxResults(pageSize);
		List<Relation> list=crit.list();
		List<UserInfo> userList=new ArrayList<UserInfo>();
		for(Relation ra:list){
			userList.add(ra.getUserInfo());
		}
		return userList;
	}

	@Override
	public int getCount() {
		Session session=this.getSession();
		Criteria crit = session.createCriteria(UserInfo.class);
		return crit.list().size();
	}
	
	@Override
	public int getCountByGroup(Group group){
		Session session=this.getSession();
		Criteria crit = session.createCriteria(Relation.class);
		crit.add(Restrictions.eq("group", group));
		return crit.list().size();
	}

	@Override
	public boolean addRelation(List<Relation> relations) {
		// TODO Auto-generated method stub
		return false;
	}
	
	 



}
