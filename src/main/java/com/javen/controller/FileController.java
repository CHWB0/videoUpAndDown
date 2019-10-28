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
import com.javen.service.FileService;
import com.javen.util.FileUploadTool;
import com.javen.util.ImageUtils;
/**
 * @author CHWB
 *
 */
@Controller  
@RequestMapping("/video")
public class FileController {
	@Autowired
	private FileService fileservice;
	/*
	 * 进入到上传页面
	 * 
	 * */
	@RequestMapping("videoUpload")
	public String login() {
		return "videoUpload";
	}
	/*
	 * 上传文件
	 * 
	 * */
	@RequestMapping(value = "/upload_video")
	@ResponseBody
	public ModelAndView upload(@RequestParam("file") MultipartFile videoFile,@RequestParam("pictureFile") MultipartFile pictureFile,
			HttpServletRequest request, ModelMap map) {
		String message = "";
		FileEntity entity = new FileEntity();
		String logoPathDir = request.getParameter("shipin");
		//System.out.println("========"+logoPathDir);
		FileUploadTool fileUploadTool = new FileUploadTool();
		ImageUtils imageUtils = new ImageUtils();
		try {
		    entity = fileUploadTool.createFile(logoPathDir, videoFile, request);
			String picturePath = imageUtils.upload(request, pictureFile);
			entity.setPlayingAmount(1);
			entity.setPicturePath(picturePath);
			//System.out.println("实验图片上传"+entity);
			if (entity != null) {
				fileservice.saveFile(entity);
				message = entity.getTitleOrig()+"上传成功";
//				List<FileEntity> entity0 = fileservice.findAll();
//				map.put("entity", entity0);
				map.put("result", message);
			} else {
				message = "上传失败";
				map.put("result", message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(map);
		return new ModelAndView("videoUpload",map);
		//return new ModelAndView("videoList", map);
	}
	
	/*
	 * 查询所有视频
	 * 
	 * */
	@RequestMapping("showVieos")
	public String videoList(Model model) {
 		List<FileEntity> entity0 = fileservice.findAll();
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
		Page page = fileservice.showFiles(currentPage);
		if (page != null) {
			model.addAttribute("page", page);
			List<FileEntity> entity0 = fileservice.getFileByPage(page);
			model.addAttribute("entity", entity0);
		}
		return "videoList";
	}
	/*
	 * 查询所有视频
	 * 
	 * */
	@RequestMapping("videoList1")
	@ResponseBody
	public List<FileEntity> videoList1(Model model) {
 		List<FileEntity> entity0 = fileservice.findAll();
 		model.addAttribute("entity", entity0);
		return entity0;
	}
	/*
	 * 查询部分视频显示在首页
	 * 
	 * */
	@RequestMapping(value = "videoListIndex/{currentPage}")
	public String videoListIndex(@PathVariable("currentPage") int currentPage, Model model) {
		Page page = fileservice.showFiles(currentPage);
		if (page != null) {
			model.addAttribute("page", page);
			List<FileEntity> entity0 = fileservice.getFileByPage(page);
			if (entity0.size() > 0) {
				model.addAttribute("entity", entity0);
			}else {
				return null;
			}
		}
		return "videoListIndex";
	}
	/*
	 * 分页查询所有视频
	 * 
	 * */
	@RequestMapping(value = "videoListUserIndex/{currentPage}")
	public String videoListUserIndex(@PathVariable("currentPage") int currentPage, Model model) {
		Page page = fileservice.showFiles(currentPage);
		if (page != null) {
			model.addAttribute("page", page);
			List<FileEntity> entity0 = fileservice.getFileByPage(page);
			if (entity0.size() > 0) {
				model.addAttribute("entity", entity0);
			}else {
				return null;
			}
		}
		return "videoListUserIndex";
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
		List<FileEntity> fileEntities = fileservice.getFileByFileUploadTime(uploadTime);
		model.addAttribute("fileEntity",fileEntities);
		return "videoListByDate";
	}
	/*
	 * 删除某一视频
	 * 
	 * */
	@RequestMapping("videoDelete")
	public String deleteVideo(Integer fileId,@RequestParam("currentPage") Integer currentPage, Model model,HttpServletRequest request){
		//System.out.println(fileId);
		FileEntity entity = fileservice.findByid(fileId);
		String videoPath = entity.getPath();
		//System.out.println(videoPath);
		String picturePath = entity.getPicturePath();
		int deleteVideo = fileservice.deleteFileById(fileId);
		if(deleteVideo <= 0){
			return null;
		}else {
			File videoFile = new File(videoPath);
			System.out.println(videoFile);
			if (videoFile.exists()) {
				videoFile.delete();
			}
			File pictureFile = new File(picturePath);
			System.out.println(pictureFile);
			if (pictureFile.exists()) {
				pictureFile.delete();
			}
			return this.showVideos(currentPage, model);
		}
	}
	/*
	 * 显示修改页面
	 * 
	 * */
	@RequestMapping("videoUpdate")
	public String videoUpdate(@RequestParam("fileId") Integer fileId,@RequestParam("currentPage") Integer currentPage,Model model,HttpSession session){
		FileEntity fileEntity = fileservice.findByid(fileId);
		session.setAttribute("currentPage", currentPage);
		/*session.setAttribute("fileId", fileId);
		session.setAttribute("titleAlter", fileEntity.getTitleAlter());
		session.setAttribute("size", fileEntity.getSize());
		session.setAttribute("path", fileEntity.getPath());
		session.setAttribute("uploadTime", fileEntity.getUploadTime());*/
		model.addAttribute("video", fileEntity);
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
		int i = fileservice.updateFileById(fileEntity);
		if (i <= 0) {
			return null;
		}else {
			return this.showVideos(currentPage,model);
		}
	}
	/*
	 * 按上传日期分期显示视频
	 * 
	 * */
	@RequestMapping("showIndex")
	public ModelAndView videoListByGroupDate(ModelMap map){
		List<FileEntity> fileEntities = fileservice.getVideoByPlayingAmount();//查询出视频点击量最高的5个视频
		map.put("fileEntity", fileEntities);
		/* 显示最后三天上传的视频 */
		List<FileEntity> dates = fileservice.getFileUploadTime();
		if(dates.size()>0){
			String day1 = dates.get(0).getUploadTime();
			map.put("day1", day1);
			List<FileEntity> entityDay1 = fileservice.getFileByFileUploadTime(day1);
			map.put("entityDay1", entityDay1);
			if (entityDay1.size()>0) {
				FileEntity entity = entityDay1.get(0);
				map.put("entity", entity);//播放界面显示最后一天最后上传的一个视频
			}
		}
		if(dates.size()>1){
			String day2 = dates.get(1).getUploadTime();
			map.put("day2", day2);
			List<FileEntity> entityDay2 = fileservice.getFileByFileUploadTime(day2);
			map.put("entityDay2", entityDay2);
		}
		if(dates.size()>2){
			String day3 = dates.get(2).getUploadTime();
			map.put("day3", day3);
			List<FileEntity> entityDay3 = fileservice.getFileByFileUploadTime(day3);
			map.put("entityDay3", entityDay3);
		}
		//System.out.println(map);
		return new ModelAndView("index",map);
	}
	
	/*
	 * 实时更新视频的点击量
	 * 
	 * */
    @RequestMapping("updateVideoPlayingAmount")
	@ResponseBody
	public int updateVideoPlayingAmount(Integer fileId){
    	int result = 0;
    	int playingAmount = 0;
    	if (fileId != null) {
    		FileEntity fileEntity = fileservice.findByid(fileId);
    		playingAmount = fileEntity.getPlayingAmount() + 1;
    		fileEntity.setPlayingAmount(playingAmount);
    		int updateFlag = fileservice.updateVideoPlayingAmount(fileEntity);
    		if (updateFlag <= 0) {
    			result = 0;
    		}else {
    			result = 1;
    		}
		}
    	return playingAmount;
	}
    
    /*
	 * 实时更新视频的点击量
	 * 
	 * */
    @RequestMapping(value = "getVideoPlayingAmount/{fileId}")
	@ResponseBody
	public int getVideoPlayingAmount(@PathVariable("fileId") Integer fileId){
    	int playingAmount = 0;
    	if (fileId != null) {
    		FileEntity fileEntity = fileservice.findByid(fileId);
    			playingAmount = fileEntity.getPlayingAmount();
		}
    	return playingAmount;
	}
}
