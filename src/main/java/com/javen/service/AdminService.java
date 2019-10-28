package com.javen.service;

import java.util.List;

import com.javen.model.Admin;

public interface AdminService {
	public int insertAdmin(Admin admin);
	public Admin getAdminByNo(Integer aNo);
	public List<Admin> getAdminAll();
	public int deleteAdminByNo(Integer aNo);
	public int updateAdminByNo(Admin admin);
}
