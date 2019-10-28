package com.javen.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.javen.dao.ProductDao;
import com.javen.model.Product;
import com.javen.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	public List<Product> list() {
		return productDao.list();
	}

	@Transactional
	public String save(MultipartFile file, Product product, ModelMap map) {
		
		// 保存图片的路径，图片上传成功后，将路径保存到数据库
		String filePath = "F:\\upload";
		// 获取原始图片的扩展名
		String originalFilename = file.getOriginalFilename();
		// 生成文件新的名字
		String newFileName = UUID.randomUUID() + originalFilename;
		// 封装上传文件位置的全路径
		File targetFile = new File(filePath, newFileName);
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		// 保存到数据库
		product.setPimage(newFileName);
		productDao.save(product);
		return "redirect:/listImages";
	}

}
