package com.javen.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javen.model.User;
import com.javen.service.UserService;

/**
 * @author CHWB
 *用户登录控制
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	@Autowired
	UserService userService;
	/**
	 * @author CHWB
	 *显示用户登录界面
	 */
	@RequestMapping("showLogin")
	public String showLogin(){
		return "login";
	}
	/**
	 * @author CHWB
	 *用户登录
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
	@ResponseBody
	public int doLogin(@RequestBody User user,HttpSession session){
		session.removeAttribute("user");//在每一次用户重新登录时，都把session里面缓存的用户信息清空一次
		int result=0;
		User ur = userService.getUserByuNo(user.getuNo());
		if (ur!=null&&ur.getuType()==1) {
			if (ur.getPassword().equals(user.getPassword())&&ur.getuNo().equals(user.getuNo())) {
				session.setAttribute("user", ur);
				session.setAttribute("uId", ur.getuId());
				session.setAttribute("uNo",ur.getuNo());
				session.setAttribute("userName", ur.getUserName());
				session.setAttribute("LOGIN_USER", ur);
				result = 1;
			}else {
				result = 3;
			}
			
		}else if (ur!=null&&ur.getuType()==2) {
			if (ur.getuNo().equals(user.getuNo())&&ur.getPassword().equals(user.getPassword())) {
				session.setAttribute("user", ur);
				session.setAttribute("sessionId", ur.getuId());
				session.setAttribute("uNo",ur.getuNo());
				session.setAttribute("userName", ur.getUserName());
				session.setAttribute("LOGIN_USER", ur);
				result = 2;
			}else {
				result = 3;
			}
		}else {
			result = 3;
		}
		return result;
	}
	/**
	 * @author CHWB
	 *用户注册重复验证
	 */
	@RequestMapping("userChecking")
	@ResponseBody
	public int  userChecking(String uNo){
		System.out.println(uNo);
 		User ur = userService.getUserByuNo(uNo);
		System.out.println(ur);
		int result = 2;
		if(ur == null){
			result = 0;
		}else{
			result = 1;
		}
		return result;
	}
	
}
