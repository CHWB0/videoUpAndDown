package com.javen.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javen.dao.UvRelationDao;
import com.javen.model.UvRelation;
import com.javen.service.UvRelationService;
@Service("uvRelationService")
public class UvRelationServiceImpl implements UvRelationService {
	@Autowired
	UvRelationDao uvRelationDao;
	public List<Integer> getVideoIdByUserHistory(Integer uId) {
		return this.uvRelationDao.getVideoIdByUserHistory(uId);
	}
	public int insertUvRelation(UvRelation uvRelation) {
		return this.uvRelationDao.insertUvRelation(uvRelation);
	}

}
