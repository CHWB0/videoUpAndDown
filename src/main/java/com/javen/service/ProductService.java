package com.javen.service;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.javen.model.Product;

public interface ProductService {
	public List<Product> list();
	public String save(MultipartFile file, Product product, ModelMap map);
}
