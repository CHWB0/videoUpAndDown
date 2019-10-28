package com.javen.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javen.dao.AdminDao;
import com.javen.model.Admin;
import com.javen.service.AdminService;
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Resource
	AdminDao adminDao=null;
	public int insertAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return this.adminDao.insertAdmin(admin);
	}

	public Admin getAdminByNo(Integer aNo) {
		return this.adminDao.getAdminByNo(aNo);
	}

	public List<Admin> getAdminAll() {
		// TODO Auto-generated method stub
		return this.adminDao.getAdminAll();
	}

	public int updateAdminByNo(Admin admin) {
		// TODO Auto-generated method stub
		return this.adminDao.updateAdminByNo(admin);
	}

	public int deleteAdminByNo(Integer aNo) {
		// TODO Auto-generated method stub
		return this.adminDao.deleteAdminByNo(aNo);
	}

}
