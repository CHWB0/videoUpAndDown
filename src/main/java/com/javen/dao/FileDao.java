package com.javen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javen.model.FileEntity;
import com.javen.model.Page;
@Repository
public interface FileDao {
	void saveFile(FileEntity entity);
	FileEntity findByid(long id);
	List<FileEntity> findAll();
	
	List<Map<String, List<Integer>>> getGroupFileByDate();
	List<FileEntity> getFileUploadTime();
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
    List<FileEntity> getFileByPage(Page page);
    
	List<FileEntity> getFileByFileName(String name);
	List<FileEntity> getFileByFileType(String type);
	List<FileEntity> getFileByFileUploadTime(String time);
	int deleteFileById(long id);
	int updateFileById(FileEntity fileEntity);
	
	/*****
     * 实时更新记录视频的点击量
	 * @return 
     ***/
	int updateVideoPlayingAmount(FileEntity fileEntity);
	/*****
     * 按点击量获取最常播放的五个视频
     ***/
	List<FileEntity> getVideoByPlayingAmount();
	
}
