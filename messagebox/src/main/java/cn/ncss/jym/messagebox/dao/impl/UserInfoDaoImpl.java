package cn.ncss.jym.messagebox.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.UserInfo;
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
	public void addOne(UserInfo userInfo) {
		this.getSession().save(userInfo);
	}

	@Override
	public void deleteOne(UserInfo userInfo) {
		this.getSession().delete(userInfo);
	}

	@Override
	public void update(UserInfo userInfo) {
		this.getSession().update(userInfo);
	}

	@Override
	public UserInfo get(String areaCode, String orgCode, String orgName, String fxmc, String type) {
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
		if (StringUtil.hasText(type)) {
			crit.add(Restrictions.eq("userType", type));
		}
		return (UserInfo) crit.uniqueResult();
	}
}
