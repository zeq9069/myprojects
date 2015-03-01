package com.zhiweifenxi.web.dao;

import java.util.List;

import com.zhiweifenxi.web.pojo.Company;

/**
 * 公司
 * @author kyrin
 */
public interface CompanyDao {

	public void add(Company company);
	
	public void add(List<Company> list);
	
	public boolean delete(Company company);
	
	public boolean update(Company company);
	
	/**
	 * 获取公司
	 * @param company_url 公司站点URL
	 * @return
	 */
	public Company get(String company_url);
	
	/**
	 *根据招聘网站的公司URL获取
	 * @param url 招聘网站上的公司URL
	 * @return
	 */
	public Company getByUrl(String url);
	
}
