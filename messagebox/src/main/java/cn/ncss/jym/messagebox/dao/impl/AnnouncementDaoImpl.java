package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.utils.SchoolType;

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
	public int create(Announcement announ) {
		return (int) this.getSession().save(announ);
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
	public Announcement get(int id) {
		return (Announcement) this.getSession().get(Announcement.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getListByUser(UserInfo userInfo,int currentIndex,int pageSize) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("user", userInfo));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"),"id");
		pro.add(Projections.property("title"),"title");
		pro.add(Projections.property("date"),"date");
		pro.add(Projections.property("user"),"user");
		pro.add(Projections.property("type"),"type");
		crit.setProjection(pro);
		crit.setResultTransformer(Transformers.aliasToBean(Announcement.class));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	
	@Override
	public long getCountByUser(UserInfo userInfo){
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("user", userInfo));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (long) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getListByType(UserInfo userInfo,String type, int currentIndex,int pageSize) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.like("type", type,MatchMode.ANYWHERE));
		crit.add(Restrictions.eq("user", userInfo));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"));
		pro.add(Projections.property("title"));
		pro.add(Projections.property("date"));
		pro.add(Projections.property("user"));
		pro.add(Projections.property("type"));
		crit.setProjection(pro);
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveByYxdm(List<String> typeList,
			String provinceCode,String yxdm,int currentIndex,int pageSize) {
		String hql="select announ.id as id, announ.title as title, announ.date as date, announ.user as user, announ.type as type from Announcement announ "
				+ "where (announ.publish_role='province' and announ.publish_dm=:publish_dm and (announ.targetYxdm like '%"+yxdm+"%'";
		
		String str=" or announ.targetYxlx like '%"+SchoolType.SCHOOL_ALL.value()+"%'";
		for(String type:typeList){
			str+=" or announ.targetYxlx like '%"+type+"%'";
		}
		hql=hql+str+" )) or (announ.publish_role='school' and announ.targetYxdm like '%"+yxdm+"%' )";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("publish_dm", provinceCode);
		query.setFirstResult((currentIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.aliasToBean(Announcement.class));
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveByYxdm(List<String> typeList,String provinceCode,String yxdm) {
		String hql="select announ.id as id, announ.title as title, announ.date as date, announ.user as user, announ.type as type from Announcement announ "
				+ "where (announ.publish_role='province' and announ.publish_dm=:publish_dm and (announ.targetYxdm like '%"+yxdm+"%'";
		
		String str=" or announ.targetYxlx like '%"+SchoolType.SCHOOL_ALL.value()+"%'";
		for(String type:typeList){
			str+=" or announ.targetYxlx like '%"+type+"%'";
		}
		hql=hql+str+" )) or (announ.publish_role='school' and announ.targetYxdm like '%"+yxdm+"%' )";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("publish_dm", provinceCode);
		query.setResultTransformer(Transformers.aliasToBean(Announcement.class));
		return query.list();
	}


	@Override
	public int getCount(List<String> typeList, String provinceCode,String yxdm) {
		String hql="select announ.id as id, announ.title as title, announ.date as date, announ.user as user, announ.type as type from Announcement announ "
				+ "where (announ.publish_role='province' and announ.publish_dm=:publish_dm and (announ.targetYxdm like '%"+yxdm+"%'";
		
		String str=" or announ.targetYxlx like '%"+SchoolType.SCHOOL_ALL.value()+"%'";
		for(String type:typeList){
			str+=" or announ.targetYxlx like '%"+type+"%'";
		}
		hql=hql+str+" )) or (announ.publish_role='school' and announ.targetYxdm like '%"+yxdm+"%' )";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("publish_dm", provinceCode);
		query.setResultTransformer(Transformers.aliasToBean(Announcement.class));
		return query.list().size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveByProvince(String provinceCode,int currentIndex,int pageSize) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.like("targerProvinceCode", provinceCode));
		crit.add(Restrictions.eq("publish_role","province"));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"));
		pro.add(Projections.property("title"));
		pro.add(Projections.property("date"));
		pro.add(Projections.property("user"));
		pro.add(Projections.property("type"));
		crit.setProjection(pro);
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveByProvince(String provinceCode) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.like("targerProvinceCode", provinceCode));
		crit.add(Restrictions.eq("publish_role","province"));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"));
		pro.add(Projections.property("title"));
		pro.add(Projections.property("date"));
		pro.add(Projections.property("user"));
		pro.add(Projections.property("type"));
		crit.setProjection(pro);
		return crit.list();
	}

	@Override
	public int getCount(String provinceCode) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.like("targerProvinceCode", provinceCode));
		crit.add(Restrictions.eq("publish_role","province"));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (int) crit.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveBySzyx(String yxdm, String szyx,int currentIndex,int pageSize) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("publish_role","school"));
		crit.add(Restrictions.eq("publish_dm", yxdm));
		crit.add(Restrictions.like("targerSzyx", szyx));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"));
		pro.add(Projections.property("title"));
		pro.add(Projections.property("date"));
		pro.add(Projections.property("user"));
		pro.add(Projections.property("type"));
		crit.setProjection(pro);
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveBySzyx(String yxdm, String szyx) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("publish_role","school"));
		crit.add(Restrictions.eq("publish_dm", yxdm));
		crit.add(Restrictions.like("targerSzyx", szyx));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"));
		pro.add(Projections.property("title"));
		pro.add(Projections.property("date"));
		pro.add(Projections.property("user"));
		pro.add(Projections.property("type"));
		crit.setProjection(pro);
		return crit.list();
	}
	
	@Override
	public int getCount(String yxdm, String szyx) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("publish_role","school"));
		crit.add(Restrictions.eq("publish_dm", yxdm));
		crit.add(Restrictions.like("targerSzyx", szyx));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (int) crit.uniqueResult();
	}


}
