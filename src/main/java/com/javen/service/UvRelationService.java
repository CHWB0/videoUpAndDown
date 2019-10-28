package com.javen.service;

import java.util.List;

import com.javen.model.UvRelation;

public interface UvRelationService {
	public int insertUvRelation(UvRelation uvRelation);
	public List<Integer> getVideoIdByUserHistory(Integer uId);
}
