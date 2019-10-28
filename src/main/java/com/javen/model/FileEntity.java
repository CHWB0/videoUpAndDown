package com.javen.model;

public class FileEntity {

	private long fileId;
    
    /*****
     * 原文件名
     ***/
    
    private String titleOrig;
 
    /*****
     * 修改后文件名
     ***/
    
    private String titleAlter;
    /*****
     * 文件对应的图片
     ***/
    
    private String picturePath;
 
    /*****
     * 文件大小
     ***/
    
    private String size;
 
    /*****
     * 文件类型
     ***/
    
    private String type;
 
    /*****
     * 文件保存路径
     ***/
    
    private String path;
 
    /*****
     * 文件上传时间
     ***/
    
    private String uploadTime;
    
    /*****
     * 被点击量
     ***/
    private Integer playingAmount; 

/*	Date date = new Date();             //获取当前默认时间——年月日小时分秒
	//SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
	//SimpleDateFormat sDateFormat = new SimpleDateFormat("MM/dd/yyyy");   //据说欧洲人喜欢的格式
    String uploadDate = sDateFormat.format(date);
	@Test
	public void test(){
		System.out.println(uploadDate);
	}
	*/
	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getTitleOrig() {
		return titleOrig;
	}

	public void setTitleOrig(String titleOrig) {
		this.titleOrig = titleOrig;
	}

	public String getTitleAlter() {
		return titleAlter;
	}

	public void setTitleAlter(String titleAlter) {
		this.titleAlter = titleAlter;
	}
	
	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	

	public Integer getPlayingAmount() {
		return playingAmount;
	}

	public void setPlayingAmount(Integer playingAmount) {
		this.playingAmount = playingAmount;
	}

	@Override
	public String toString() {
		return "FileEntity [fileId=" + fileId + ", titleOrig=" + titleOrig + ", titleAlter=" + titleAlter
				+ ", picturePath=" + picturePath + ", size=" + size + ", type=" + type + ", path=" + path
				+ ", uploadTime=" + uploadTime + ", playingAmount=" + playingAmount + "]";
	}

	

}
