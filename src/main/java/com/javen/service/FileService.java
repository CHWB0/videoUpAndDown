package com.javen.service;

import java.util.List;
import java.util.Map;

import com.javen.model.FileEntity;
import com.javen.model.Page;


public interface FileService {

	public void saveFile(FileEntity entity);

	public FileEntity findByid(long id) ;
	public List<FileEntity> findAll();
	
	public List<Map<String, List<Integer>>> getGroupFileByDate();
	public List<FileEntity> getFileUploadTime();
	
	/**
	 * 根据页码展示设备信息
	 * 
	 * @param currentPage
	 * @return
	 */
	Page showFiles(int currentPage);

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
	public List<FileEntity> getFileByPage(Page page);
	
	public List<FileEntity> getFileByFileName(String name);
	public List<FileEntity> getFileByFileType(String type);
	public List<FileEntity> getFileByFileUploadTime(String uploadTime);
	public int deleteFileById(long id);
	public int updateFileById(FileEntity fileEntity);
	
	/*****
     * 实时记录视频的点击量
	 * @return 
     ***/
	public int updateVideoPlayingAmount(FileEntity fileEntity);
	/*****
     * 按点击量获取最常播放的五个视频
     ***/
	public List<FileEntity> getVideoByPlayingAmount();
}
