package com.javen.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javen.model.FileEntity;
import com.javen.model.Page;
import com.javen.model.User;
import com.javen.service.FileService;
import com.javen.service.UserService;
import com.javen.util.FileUploadTool;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	FileService fileService;
	
	@RequestMapping("adminMenu")
	public String showAdminMenu(){
		return "adminMenu";
	}
	/**
	 * @author CHWB
	 *进入到管理员操作首页
	 */	
	@RequestMapping("showAdminIndex")
	public String AdminIndex(){
		return "adminIndex";
	}
	/*
	 * 进入到上传页面
	 * 
	 * */
	@RequestMapping("videoInsert")
	public String login() {
		return "videoInsert";
	}

	/*
	 * 上传文件
	 * 
	 * */
	@RequestMapping("upload_video")
	@ResponseBody
	public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
			HttpServletRequest request, ModelMap map) {
		String message = "";
		FileEntity entity = new FileEntity();
		String logoPathDir = request.getParameter("shipin");
		//System.out.println("-------" + logoPathDir + "----------------------------------");
		FileUploadTool fileUploadTool = new FileUploadTool();
		try {
			entity = fileUploadTool.createFile(logoPathDir, multipartFile, request);
			if (entity != null) {
				fileService.saveFile(entity);
				message = "上传成功";
				List<FileEntity> entity0 = fileService.findAll();
				if (entity0.size() > 0) {
					map.put("entity", entity0);
				}
				map.put("videoList", message);
			} else {
				message = "上传失败";
				map.put("videoList", message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(map);
		return new ModelAndView("videoList", map);
	}
	/*
	 * 查询所有视频
	 * 
	 * */
	@RequestMapping("showVideos")
	public String videoList(Model model) {
 		List<FileEntity> entity0 = fileService.findAll();
 		model.addAttribute("entity", entity0);
		return "videoList";
	}
	/**
	 * 对视频信息进行分页操作
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "videoList/{currentPage}")
	public String showVideos(@PathVariable("currentPage") int currentPage, Model model) {
		Page page = fileService.showFiles(currentPage);
		if (page != null) {
			model.addAttribute("page", page);
			List<FileEntity> entity0 = fileService.getFileByPage(page);
			model.addAttribute("entity", entity0);
		}
		return "videoList";
	}
	/*
	 * 进入到查询界面
	 * 
	 * */
	@RequestMapping("videoListByDate")
	public String videoGetByDate() {
		return "videoListByDate";
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
		return "videoListByDate";
	}
	/*
	 * 删除某一视频
	 * 
	 * */
	@RequestMapping("videoDelete")
	public String deleteVideo(@RequestParam("fileId") Integer fileId,@RequestParam("currentPage") Integer currentPage,Model model,HttpServletRequest request){
		FileEntity entity = fileService.findByid(fileId);
		String urlPath = request.getSession().getServletContext().getRealPath("/");
		String videoPath = urlPath + entity.getPath();
		System.out.println(videoPath);
		String picturePath = urlPath + entity.getPicturePath();
		int deleteVideo = fileService.deleteFileById(fileId);
		if(deleteVideo <= 0){
			return null;
		}else {
			File videoFile = new File(videoPath);
			if (videoFile.exists()) {
				videoFile.delete();
			}
			File pictureFile = new File(picturePath);
					if (pictureFile.exists()) {
						videoFile.delete();
					}
			return this.showVideos(currentPage,model);
		}
	}
	/*
	 * 显示修改页面
	 * 
	 * */
	@RequestMapping("videoUpdate")
	public String videoUpdate(@RequestParam("fileId") Integer fileId,@RequestParam("currentPage") Integer currentPage,Model model,HttpSession session){
		FileEntity fileEntity = fileService.findByid(fileId);
		session.setAttribute("currentPage", currentPage);
		/*session.setAttribute("fileId", fileId);
		session.setAttribute("titleAlter", fileEntity.getTitleAlter());
		session.setAttribute("size", fileEntity.getSize());
		session.setAttribute("path", fileEntity.getPath());
		session.setAttribute("uploadTime", fileEntity.getUploadTime());*/
		if (fileEntity != null) {
			model.addAttribute("video", fileEntity);
		}
		return "videoUpdate";
	}
	/*
	 * 修改视频信息
	 * 
	 * */
	@RequestMapping("doUpdateVideo")
	public String doVideoUpdate(FileEntity fileEntity,Model model,HttpSession session){
		//System.out.println(fileEntity);
		int currentPage = (Integer) session.getAttribute("currentPage");
		int i = fileService.updateFileById(fileEntity);
		if (i <= 0) {
			return null;
		}else {
			return this.showVideos(currentPage,model);
		}
	}
	@RequestMapping("personInfo")
	public String personInf(Model model,HttpSession session) {
		User ur = (User) session.getAttribute("user");
		//System.out.println("========="+ur);
		if (ur != null) {
			User user0 = userService.getUserById(ur.getuId());
			model.addAttribute("user", user0);
		}
 		return "personInfo";
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
			return "personInfo";
		}
 	}

}
