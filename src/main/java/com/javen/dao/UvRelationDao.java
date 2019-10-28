package com.javen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.javen.model.UvRelation;
@Repository
public interface UvRelationDao {
	int insertUvRelation(UvRelation uvRelation);
	List<Integer> getVideoIdByUserHistory(Integer uId);
}
