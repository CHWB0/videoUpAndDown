package com.javen.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javen.model.FileEntity;
import com.javen.model.Page;
import com.javen.model.User;
import com.javen.model.UvRelation;
import com.javen.service.FileService;
import com.javen.service.UserService;
import com.javen.service.UvRelationService;
import com.sun.org.apache.bcel.internal.classfile.Field;

/**
 * @author CHWB
 *
 */
@Controller  
@RequestMapping("/user")  
// /user/**
public class UserController {  
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;
	@Autowired
	private UvRelationService uvRelationService;
	/**
	 * @author CHWB
	 *进入到用户操作首页
	 */	
	@RequestMapping("showUserIndex")
	public ModelAndView videoListByGroupDate(ModelMap map){
		List<FileEntity> fileEntities = fileService.getVideoByPlayingAmount();//查询显示点击量最高的五个视频
		map.put("fileEntity", fileEntities);
		if (fileEntities.size()>0) {
			FileEntity entity = fileEntities.get(0);
			map.put("entity", entity);//播放界面显示最后一天最后上传的一个视频
		}
		/* 显示最后三天上传的视频 */
		List<FileEntity> dates = fileService.getFileUploadTime();
		if(dates.size()>0){
			String day1 = dates.get(0).getUploadTime();
			map.put("day1", day1);
			List<FileEntity> entityDay1 = fileService.getFileByFileUploadTime(day1);
			map.put("entityDay1", entityDay1);
		}
		if(dates.size()>1){
			String day2 = dates.get(1).getUploadTime();
			map.put("day2", day2);
			List<FileEntity> entityDay2 = fileService.getFileByFileUploadTime(day2);
			map.put("entityDay2", entityDay2);
		}
		if(dates.size()>2){
			String day3 = dates.get(2).getUploadTime();
			map.put("day3", day3);
			List<FileEntity> entityDay3 = fileService.getFileByFileUploadTime(day3);
			map.put("entityDay3", entityDay3);
		}
		//System.out.println(map);
		return new ModelAndView("userIndex",map);
	}
	
	/*
	 * 查询某一上传日期的视频
	 * 
	 * */
	@RequestMapping("doVideoListByDate")
	public String videoListByUploadDate(String uploadTime,Model model){
		//System.out.println(uploadTime);
		List<FileEntity> fileEntities = fileService.getFileByFileUploadTime(uploadTime);
		model.addAttribute("fileEntity",fileEntities);
		return "userVideoListByDate";
	}
	
	/**
	 * @author CHWB
	 *进入到注册页
	 */	
	@RequestMapping("showRegister")
	public String register(){
		return "register";
	}
	/**
	 * @author CHWB
	 *用户注册
	 */
	@RequestMapping("insertUser")
	public String insertUser(User user,Model model){
		
		//调用业务类，将数据发送给业务
		if (user != null) {
			userService.insertUser(user);
		}else {
			System.out.println("输入无效值");
		}
		List<User> user1 = userService.getUserAll();
			model.addAttribute("user1",user1);
		return "login";
	}
	/**
	 * 跳转 用户信息
	 * @return
	 */
	@RequestMapping("personInf")
	public String personInf(Model model,HttpSession session) {
		User ur = (User) session.getAttribute("user");
		//System.out.println("========="+ur);
		if (ur != null) {
			User user0 = userService.getUserById(ur.getuId());
			model.addAttribute("user", user0);
		}
 		return "personInfoUI";
	}
	/**
	 * 修改个人信息
	 * @return
	 */
	@RequestMapping("doUpdatePersonInf")
	public String updatePersonInf(User user,Model model){
		//System.out.println("-------"+user);
		int updateFlag = userService.updateUserById(user);
		if (updateFlag <= 0) {
			return null;
		}else {
			User ur = userService.getUserById(user.getuId());
			model.addAttribute("user", ur);
			return "personInfoUI";
		}
 	}
	/**
	 * 对视频信息进行分页操作
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userList/{currentPage}")
	public String userList(@PathVariable("currentPage") int currentPage, Model model) {
		Page page = userService.showUsers(currentPage);
		if (page != null) {
			model.addAttribute("page", page);
			List<User> user0 = userService.getUserByPage(page);
			model.addAttribute("user", user0);
		}
		return "userList";
	}
	/**
	 * @author CHWB
	 *根据ID号删除用户
	 */
	@RequestMapping("userDelete")
	public String userDelete(Integer id,@RequestParam("currentPage") Integer currentPage,Model model){
		userService.deleteUserById(id);
		return this.userList(currentPage, model);
	}
	/**
	 * @author CHWB
	 *显示修改用户信息的界面
	 */
	@RequestMapping("userUpdate")
	public String userUpdate(@RequestParam("id") Integer id,@RequestParam("currentPage") Integer currentPage, Model model,HttpSession session){
		User user = userService.getUserById(id);
		session.setAttribute("currentPage", currentPage);
		model.addAttribute("user", user);
		return "userUpdate";
	}
	/**
	 * @author CHWB
	 *修改用户信息
	 */
	@RequestMapping("doUpateUser")
	public String doupateUser (User user, Model model,HttpSession session){
		//System.out.println(user);
		int currentPage = (Integer) session.getAttribute("currentPage");
		userService.updateUserById(user);
		return this.userList(currentPage,model);
	}
	/**
	 * @author CHWB
	 *添加用户的播放历史记录
	 */
	@RequestMapping("insertUvRelation")
	public void insertUvRelation(Integer fileId,HttpSession session){
		User ur = (User) session.getAttribute("user");
		if (ur != null) {
			UvRelation uvRelation = new UvRelation();
			uvRelation.setuId(ur.getuId());
			uvRelation.setFileId(fileId);
			//System.out.println(uvRelation);
			if (uvRelation != null) {
				uvRelationService.insertUvRelation(uvRelation);
			}
		}else {
			System.out.println("没有用户登录，session中无数据，so无需把此次观看记录存放到数据库");
		}
	}
	/**
	 * @author CHWB
	 *获取用户的播放历史记录
	 */
	@RequestMapping("videoPlayingHistory")
	@ResponseBody
	public ModelAndView videoPlayingHistory(ModelMap map,HttpSession session){
		User ur = (User) session.getAttribute("user");
		//System.out.println(ur);
		if (ur != null) {
			List<Integer> fileIds = uvRelationService.getVideoIdByUserHistory(ur.getuId());
			//System.out.println(fileIds);
			if (fileIds.size() > 0) {
				List<FileEntity> fileEntities = new ArrayList<FileEntity>();
				for (Integer fileId : fileIds) {
					FileEntity fileEntity = new FileEntity();
					if((fileEntity = fileService.findByid(fileId)) != null){
						fileEntities.add(fileEntity);
					}
				}
				//System.out.println(fileEntities);
				map.put("fileEntity", fileEntities);
			}
		}
		return new ModelAndView("videoPlayingHistory",map);
	}
	/**
	 * @author CHWB
	 *获取用户的播放历史记录
	 */
	@RequestMapping("videoPlayingHistory0")
	@ResponseBody
	public List<FileEntity> videoPlayingHistory0(HttpSession session){
		User ur = (User) session.getAttribute("user");
		List<FileEntity> fileEntities = new ArrayList<FileEntity>();
		if (ur != null) {
			List<Integer> fileIds = uvRelationService.getVideoIdByUserHistory(ur.getuId());
			if (fileIds.size() > 0) {
				for (Integer fileId : fileIds) {
					FileEntity fileEntity = new FileEntity();
					if((fileEntity = fileService.findByid(fileId)) != null){
						fileEntities.add(fileEntity);
					}
				}
			}
		}
		return fileEntities;
	}
}  