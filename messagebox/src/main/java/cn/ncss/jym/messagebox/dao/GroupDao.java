package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Group;

/**
 * *************************
 *    用户组的持久层接口
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
public interface GroupDao {

	/**
	 * 添加group
	 * @param group
	 * @return 返回ID
	 */
	public String add(Group group);

	public boolean delete(String name);

	public boolean update(Group group);

	public Group get(String name);

	public List<Group> getList();

	public List<Object[]> getGroupInfo();

	public boolean isExists(String name);

}
