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

	public void add(Group group);

	public void delete(Group group);

	public void update(Group group);

	public Group get(String name);

	public List<Group> getList();

}
