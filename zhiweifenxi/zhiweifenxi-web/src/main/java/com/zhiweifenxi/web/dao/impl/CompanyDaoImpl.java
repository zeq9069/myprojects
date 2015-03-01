package com.zhiweifenxi.web.dao.impl;

import java.util.List;
import junit.framework.Assert;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhiweifenxi.web.dao.CompanyDao;
import com.zhiweifenxi.web.pojo.Company;

/**
 * 公司持久层实现
 * @author kyrin
 */
public class CompanyDaoImpl implements CompanyDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	public void add(Company company) {
		this.getSession().save(company);
	}

	public void add(List<Company> list) {
		Assert.assertNotNull(list);
		Session session=this.getSession();
		int flag=0;
		for(Company company:list){
			session.save(company);
			flag++;
			if(flag%5==0){
				session.flush();
			}
		}
	}

	public boolean delete(Company company) {
		this.getSession().delete(company);
		return true;
	}

	public boolean update(Company company) {
		this.getSession().update(company);
		return true;
	}

	public Company get(String company_url) {
		Criteria cri=this.getSession().createCriteria(Company.class);
		cri.add(Restrictions.eq("company_url", company_url));
		return (Company) cri.uniqueResult();
	}

	public Company getByUrl(String url) {
		Criteria cri=this.getSession().createCriteria(Company.class);
		cri.add(Restrictions.eq("url", "url"));
		return (Company) cri.uniqueResult();
	}
}
