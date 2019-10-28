package com.javen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.javen.model.Product;
@Repository
public interface ProductDao {
	
	/**
	 * 查询所有的图片
	 * @return
	 */
	List<Product> list();
	
	/**
	 * 上传一张图片
	 * @param product
	 * @return
	 */
	Integer save(Product product);
}
