package com.javen.model;
import java.util.List;
public class Page {
	    private int startIndex;        //数据库从哪行开始获取
	    private int pageSize;          //每页的大小
	    private int currentPage;       //所在当前页
	    private int totalDataCount;    //总数据数是从数据库获取到多少条数据
	    private int totalPage;         //总页数
	    private List<FileEntity> videoList;    //封装设备信息
	    private List<User> userList;        //封装租赁信息
	    public Page() {}
		public int getStartIndex() {
			return startIndex;
		}
		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}
		public int getTotalDataCount() {
			return totalDataCount;
		}
		public void setTotalDataCount(int totalDataCount) {
			this.totalDataCount = totalDataCount;
		}
		public int getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public List<FileEntity> getVideoList() {
			return videoList;
		}
		public void setVideoList(List<FileEntity> videoList) {
			this.videoList = videoList;
		}
		public List<User> getUserList() {
			return userList;
		}
		public void setUserList(List<User> userList) {
			this.userList = userList;
		}
		@Override
		public String toString() {
			return "Page [startIndex=" + startIndex + ", pageSize=" + pageSize + ", currentPage=" + currentPage
					+ ", totalDataCount=" + totalDataCount + ", totalPage=" + totalPage + ", videoList=" + videoList
					+ ", userList=" + userList + "]";
		}
}
