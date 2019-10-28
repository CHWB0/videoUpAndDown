package com.javen.model;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pid;
	
	private String pimage;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pimage=" + pimage + "]";
	}
	
}
