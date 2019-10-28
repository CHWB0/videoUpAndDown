package com.javen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.javen.model.Page;
import com.javen.model.User;
@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User getUserByUno(String uNo);
    
    User getUserById(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User user);
    
    List<User> getUserByUserName(String username);
    
    List<User> getUserAll();
    
	/**
	 * 获取视频文件数据总量
	 * @return
	 */
	int getTotalDataCount();
	/**
	 * 根据页码进行分页显示
	 * @param page
	 * @return
	 */
    List<User> getUserByPage(Page page);
}
