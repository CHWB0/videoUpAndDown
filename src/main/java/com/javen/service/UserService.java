package com.javen.service;

import java.util.List;

import com.javen.model.Page;
import com.javen.model.User;

public interface UserService {
	public User getUserByuNo(String uNo);
	public User getUserById(Integer id);
	public List<User> getUserByUserName(String username);
	public List<User> getUserAll();
	public int insertUser(User record);
	public int insertSelective(User record);
	public int deleteUserById(Integer userId);
	public int updateUserById(User user);
	public int updateUserByIdSelective(User record);
	/**
	 * 根据页码展示设备信息
	 * 
	 * @param currentPage
	 * @return
	 */
	Page showUsers(int currentPage);

	/**
	 * 获取数据总量
	 * 
	 * @return
	 */
	int getTotalDataCount();

	/**
	 * 根据页码进行分页
	 * 
	 * @param page
	 * @return
	 */
	List<User> getUserByPage(Page page);
}
