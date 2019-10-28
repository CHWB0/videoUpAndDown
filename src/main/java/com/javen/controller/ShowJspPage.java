package com.javen.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javen.model.FileEntity;
import com.javen.service.FileService;

@Controller
public class ShowJspPage {
	@Autowired
	FileService fileservice;
	/*
	 * 按上传日期分期显示视频
	 * 
	 * */
	@RequestMapping("showIndex")
	public ModelAndView videoListByGroupDate(ModelMap map,HttpSession session){
		session.removeAttribute("user");//在每一次用户退出到初始页面时，都把session里面的用户信息清空一次
		List<FileEntity> fileEntities = fileservice.getVideoByPlayingAmount();//查询出视频点击量最高的5个视频
		map.put("fileEntity", fileEntities);
		if (fileEntities.size()>0) {
			FileEntity entity = fileEntities.get(0);
			map.put("entity", entity);//播放界面显示最后一天最后上传的一个视频
		}
		/* 显示最后三天上传的视频 */
		List<FileEntity> dates = fileservice.getFileUploadTime();
		if(dates.size()>0){
			String day1 = dates.get(0).getUploadTime();
			map.put("day1", day1);
			List<FileEntity> entityDay1 = fileservice.getFileByFileUploadTime(day1);
			map.put("entityDay1", entityDay1);
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
}
