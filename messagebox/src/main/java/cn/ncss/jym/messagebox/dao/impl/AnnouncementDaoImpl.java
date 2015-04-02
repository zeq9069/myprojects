package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
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
	public List<Announcement> getListByUser(String publisherId,int currentIndex,int pageSize) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("publisher", publisherId));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	
	@Override
	public long getCountByUser(String publisherId){
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("publisher", publisherId));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (long) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getListByType(String publisherId,String type, int currentIndex,int pageSize) {
		Criteria crit = this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.like("type", type,MatchMode.ANYWHERE));
		crit.add(Restrictions.eq("publisher", publisherId));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveByYxdm(List<String> typeList,
			String yxdm,int currentIndex,int pageSize) {
		String hql="from Announcement where targetYxdm like '%"+yxdm+"%'";
		
		String str=" or targetYxlx like '%"+SchoolType.SCHOOL_ALL.value()+"%'";
		for(String type:typeList){
			str+=" or targetYxlx like '%"+type+"%'";
		}
		hql=hql+str;
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((currentIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveByYxdm(List<String> typeList,String yxdm) {
		String hql="select id,title,date,publisher,type from Announcement where targetYxdm like '%"+yxdm+"%'";
		
		String str=" or targetYxlx like '%"+SchoolType.SCHOOL_ALL.value()+"%'";
		for(String type:typeList){
			str+=" or targetYxlx like '%"+type+"%'";
		}
		hql=hql+str;
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}


	@Override
	public int getCount(List<String> typeList, String yxdm) {
		String hql="from Announcement where targetYxdm like '%"+yxdm+"%'";
		
		String str=" or targetYxlx like '%"+SchoolType.SCHOOL_ALL.value()+"%'";
		for(String type:typeList){
			str+=" or targetYxlx like '%"+type+"%'";
		}
		hql=hql+str;
		Query query=this.getSession().createQuery(hql);
		return query.list().size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveByProvince(String provinceCode,int currentIndex,int pageSize) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.like("targerProvinceCode", provinceCode));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveByProvince(String provinceCode) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.like("targerProvinceCode", provinceCode));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"));
		pro.add(Projections.property("title"));
		pro.add(Projections.property("date"));
		pro.add(Projections.property("publisher"));
		pro.add(Projections.property("type"));
		crit.setProjection(pro);
		return crit.list();
	}

	@Override
	public int getCount(String provinceCode) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.like("targerProvinceCode", provinceCode));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (int) crit.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveBySzyx(String yxdm, String szyx,int currentIndex,int pageSize) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("targerYxdm", yxdm));
		crit.add(Restrictions.like("targerSzyx", szyx));
		crit.setFirstResult((currentIndex-1)*pageSize);
		crit.setMaxResults(pageSize);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getReceiveBySzyx(String yxdm, String szyx) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("targerYxdm", yxdm));
		crit.add(Restrictions.like("targerSzyx", szyx));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.property("id"));
		pro.add(Projections.property("title"));
		pro.add(Projections.property("date"));
		pro.add(Projections.property("publisher"));
		pro.add(Projections.property("type"));
		crit.setProjection(pro);
		return crit.list();
	}
	
	@Override
	public int getCount(String yxdm, String szyx) {
		Criteria crit=this.getSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("targerYxdm", yxdm));
		crit.add(Restrictions.like("targerSzyx", szyx));
		ProjectionList pro=Projections.projectionList();
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return (int) crit.uniqueResult();
	}


}
