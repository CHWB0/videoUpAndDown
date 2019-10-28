package com.javen.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javen.dao.FileDao;
import com.javen.model.FileEntity;
import com.javen.model.Page;
import com.javen.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService{
	@Autowired
	private FileDao fileDao;
	public void saveFile(FileEntity entity) {
		fileDao.saveFile(entity);
		
	}

	public FileEntity findByid(long id) {
		
		return fileDao.findByid(id);
	}

	public List<FileEntity> findAll() {
		
		return fileDao.findAll();
	}

	public List<FileEntity> getFileByFileName(String name) {
		return fileDao.getFileByFileName(name);
	}

	public List<FileEntity> getFileByFileType(String type) {
		return fileDao.getFileByFileType(type);
	}

	public int deleteFileById(long id) {
		return fileDao.deleteFileById(id);
	}

	public List<FileEntity> getFileByFileUploadTime(String time) {
		return fileDao.getFileByFileUploadTime(time);
	}

	public int updateFileById(FileEntity fileEntity) {
		// TODO Auto-generated method stub
		return this.fileDao.updateFileById(fileEntity);
	}

	public List<Map<String, List<Integer>>> getGroupFileByDate() {
		return this.fileDao.getGroupFileByDate();
	}

	public List<FileEntity> getFileUploadTime() {
		return this.fileDao.getFileUploadTime();
	}

	public Page showFiles(int currentPage) {
	 Page page=new Page();
        if (currentPage==0){
            currentPage=1;
        }
        //设置当前页码
        page.setCurrentPage(currentPage);
        //设置当前页大小
        page.setPageSize(5);
        //获取数据总个数
        int totalDataCount = fileDao.getTotalDataCount();
        //设置总数据个数
        page.setTotalDataCount(totalDataCount);
        //设置起始行
        int startIndex=(page.getCurrentPage()-1)*page.getPageSize();
        page.setStartIndex(startIndex);
        //获取要显示的数据
        List<FileEntity> videoList = fileDao.getFileByPage(page);
        page.setVideoList(videoList);
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
		return fileDao.getTotalDataCount();
	}
	public List<FileEntity> getFileByPage(Page page) {
		return fileDao.getFileByPage(page);
	}

	public int updateVideoPlayingAmount(FileEntity fileEntity) {
		return this.fileDao.updateVideoPlayingAmount(fileEntity);
		
	}

	public List<FileEntity> getVideoByPlayingAmount() {
		return this.fileDao.getVideoByPlayingAmount();
	}
}