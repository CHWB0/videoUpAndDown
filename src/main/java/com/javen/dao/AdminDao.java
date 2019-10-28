package com.javen.dao;

import java.util.List;

import com.javen.model.Admin;

public interface AdminDao {
	int insertAdmin(Admin admin);
	Admin getAdminByNo(Integer aNo);
	List<Admin> getAdminAll();
	int deleteAdminByNo(Integer aNo);
	int updateAdminByNo(Admin admin);
}
