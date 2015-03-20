package cn.ncss.jym.messagebox.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ncss.jym.messagebox.dao.GroupDao;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Relation;

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
	public String add(Group group) {
		return this.getSessioin().save(group).toString();
	}

	@Override
	public boolean delete(int group_id) {
		String hql = "delete from Group where id=:id";
		Query query = this.getSessioin().createQuery(hql);
		query.setParameter("id", group_id);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean update(Group group) {
		this.getSessioin().update(group);
		return true;
	}

	@Override
	public Group get(String name) {
		Criteria crit=this.getSessioin().createCriteria(Group.class);
		crit.add(Restrictions.eq("name", name));
		return (Group) crit.uniqueResult();
		
	}
	
	@Override
	public Group get(int group_id) {
		return (Group) this.getSessioin().get(Group.class, group_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getList() {
		Criteria crit = this.getSessioin().createCriteria(Group.class);
		return crit.list();
	}

	@Override
	public boolean isExists(int group_id) {
		return this.getSessioin().get(Group.class, group_id) == null ? false : true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getGroupInfo() {
		Criteria crit = this.getSessioin().createCriteria(Relation.class);
		ProjectionList pro = Projections.projectionList();
		pro.add(Projections.groupProperty("group"));
		pro.add(Projections.rowCount());
		crit.setProjection(pro);
		return crit.list();
	}

	@Override
	public boolean isExists(String groupName) {
		Criteria crit=this.getSessioin().createCriteria(Group.class);
		crit.add(Restrictions.eq("name", groupName));
		return crit.list().size()>0?true:false;
	}
}
