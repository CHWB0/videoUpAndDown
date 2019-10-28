package com.javen.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javen.dao.UserDao;
import com.javen.model.FileEntity;
import com.javen.model.Page;
import com.javen.model.User;
import com.javen.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	public User getUserByuNo(String uNo) {
		return userDao.getUserByUno(uNo);
	}
	public List<User> getUserByUserName(String username) {
		return this.userDao.getUserByUserName(username);
	}
	public List<User> getUserAll() {
		return this.userDao.getUserAll();
	}
	public int insertUser(User record) {
		return this.userDao.insert(record);
	}
	public int insertSelective(User record) {
		return 0;
	}
	public int deleteUserById(Integer userId) {
		return this.userDao.deleteByPrimaryKey(userId);
	}
	public int updateUserByIdSelective(User record) {
		return 0;
	}
	public int updateUserById(User user) {
		return this.userDao.updateByPrimaryKey(user);
	}
	public User getUserById(Integer id) {
		return this.userDao.getUserById(id);
	}
	public Page showUsers(int currentPage) {
		Page page=new Page();
        if (currentPage==0){
            currentPage=1;
        }
        //设置当前页码
        page.setCurrentPage(currentPage);
        //设置当前页大小
        page.setPageSize(5);
        //获取数据总个数
        int totalDataCount = userDao.getTotalDataCount();
        //设置总数据个数
        page.setTotalDataCount(totalDataCount);
        //设置起始行
        int startIndex=(page.getCurrentPage()-1)*page.getPageSize();
        page.setStartIndex(startIndex);
        //获取要显示的数据
        List<User> userList = userDao.getUserByPage(page);
        page.setUserList(userList);
        //设置总页数
        int totalPage;
        if(totalDataCount%page.getPageSize()==0){
            totalPage= totalDataCount/page.getPageSize();
        }else{
            totalPage= totalDataCount/page.getPageSize()+1;
        }
        page.setTotalPage(totalPage);
        return page;
	}
	public int getTotalDataCount() {
		return this.userDao.getTotalDataCount();
	}
	public List<User> getUserByPage(Page page) {
		return this.userDao.getUserByPage(page);
	}

}
