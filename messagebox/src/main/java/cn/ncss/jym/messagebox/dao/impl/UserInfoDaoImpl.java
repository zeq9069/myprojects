package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getList(int page, int pageSize) {
		Session session = this.getSession();
		Criteria crit = session.createCriteria(UserInfo.class);
		crit.setFirstResult((page - 1) * pageSize);
		crit.setMaxResults(pageSize);
		List<UserInfo> list = crit.list();
		return list;
	}

	@Override
	public int getCount() {
		Session session = this.getSession();
		Criteria crit = session.createCriteria(UserInfo.class);
		return crit.list().size();
	}
	@Override
	public UserInfo getById(String id) {
		return (UserInfo) this.getSession().get(UserInfo.class, id);
	}

	@Override
	public boolean addRecord(Record record) {
		Session session = this.getSession();
		session.save(record);
		session.flush();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getCurrentProvinceSchoolAndAllProvinceUsers(
			String currentProvinceCode, String userID) {
		Criteria cri=this.getSession().createCriteria(UserInfo.class);
		cri.add(Restrictions.and(Restrictions.eq("areaCode",currentProvinceCode), Restrictions.eq("userType", "school")));
		cri.add(Restrictions.and(Restrictions.ne("id",userID), Restrictions.eq("userType", "province")));
		return cri.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getCurrentProvinceSchoolAndSchoolDepartmentUsers(String provinceCode,
			String yxdm, String yxmc, String fxmc, String userID) {
		String hql="from UserInfo where (areaCode=:areaCode and userType='school') or (orgCode=:orgCode and orgName=:orgName and fxmc=:fxmc and userType='deparment') and id<>:id";
		
		Query query=this.getSession().createQuery(hql);
		query.setParameter("areaCode", provinceCode);
		query.setParameter("orgCode", yxdm);
		query.setParameter("orgName", yxmc);
		query.setParameter("fxmc", fxmc);
		query.setParameter("areaCode", provinceCode);
		query.setParameter("id", userID);
		List<UserInfo> list=query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getCurrentSchoolAllDepartmentUsers(String provinceCode,String yxdm,
			String yxmc, String fxmc, String userID) {
		Criteria cri=this.getSession().createCriteria(UserInfo.class);
		cri.add(Restrictions.eq("areaCode",provinceCode));
		cri.add( Restrictions.eq("orgCode",yxdm));
		cri.add( Restrictions.eq("fxmc",fxmc));
		cri.add( Restrictions.eq("orgName",yxmc));
		cri.add(Restrictions.eq("userType", "department"));
		cri.add(Restrictions.ne("id", userID));
		return cri.list();
	}
}
