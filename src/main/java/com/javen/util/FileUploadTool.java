package com.javen.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.javen.model.FileEntity;

//文件上传工具
public class FileUploadTool {

// 文件最大500M
	private static long upload_maxsize = 5000 * 1024 * 1024;
// 文件允许格式
	private static String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".xlsx", ".gif",
			".png", ".jpg", ".jpeg", ".bmp", ".xls", ".mp4", ".flv", ".ppt", ".avi", ".mpg", ".wmv", ".3gp", ".mov",
			".asf", ".asx", ".vob", ".wmv9", ".rm", ".rmvb", ".mkv" };
// 允许转码的视频格式（ffmpeg）
	private static String[] allowFLV = { ".avi", ".mpg", ".wmv", ".3gp", ".mov", ".asf", ".asx", ".vob" };
// 允许的视频转码格式(mencoder)
	private static String[] allowAVI = { ".wmv9", ".rm", ".rmvb" };
	public FileEntity createFile(String logoPathDir, MultipartFile multipartFile, HttpServletRequest request) {
		//创建和方法类一样的实体
		FileEntity entity = new FileEntity();
		boolean bflag = false;
		String fileName = multipartFile.getOriginalFilename().toString();
		// 判断文件不为空
		if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
			bflag = true;
			// 判断文件大小
			if (multipartFile.getSize() <= upload_maxsize) {
				bflag = true;
				// 文件类型判断
				if (this.checkFileType(fileName)) {
					bflag = true;
				} else {
					bflag = false;
				}
			} else {
				bflag = false;
			}
		} else {
			bflag = false;
		}
		if (bflag) {
			String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
			File logoSaveFile = new File(logoRealPathDir);
			if (!logoSaveFile.exists()) {
				logoSaveFile.mkdirs();
			}
			String name = fileName.substring(0, fileName.lastIndexOf("."));
			// 新的文件名
			String newFileName = this.getName(name);
			// 文件扩展名
			String fileEnd = this.getFileExt(fileName);
			// 绝对路径
			String fileNamedirs = logoRealPathDir + File.separator + newFileName + fileEnd;
			File filedirs = new File(fileNamedirs);
			// 转入文件
			try {
				multipartFile.transferTo(filedirs);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 相对路径
			entity.setType(fileEnd);//设置文件类型
			String fileDir = logoPathDir + newFileName + fileEnd;//存储后文件的绝对路径
			StringBuilder builder = new StringBuilder(fileDir);
			String finalFileDir = builder.substring(1);
			// size存储为String
			String size = this.getSize(filedirs);//
			// 源文件保存路径
			String aviPath = filedirs.getAbsolutePath();
			if (aviPath != null) {
				entity.setSize(size);
				entity.setPath(finalFileDir);
				entity.setTitleOrig(name);
				entity.setTitleAlter(newFileName);
				//Timestamp timestamp = new Timestamp(System.currentTimeMillis());//"YYYY-MM-dd HH:MM:SS"
				Date date = new Date();
				String uploadDate = new SimpleDateFormat("YYYY-MM-dd").format(date);//"YYYY-MM-dd"
				entity.setUploadTime(uploadDate);
				return entity;
			}
		} else {
			return null;
		}
		return entity;

	}

	/**
	 * 文件类型判断
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 视频类型判断(flv)
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkMediaType(String fileEnd) {
		Iterator<String> type = Arrays.asList(allowFLV).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileEnd.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 视频类型判断(AVI)
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkAVIType(String fileEnd) {
		Iterator<String> type = Arrays.asList(allowAVI).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileEnd.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 *
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 依据原始文件名生成新文件名 UUID：全局唯一标识符，由一个十六位的数字组成,由三部分组成：当前日期和时间、时钟序列、全局唯一的IEEE机器识别号
	 * 
	 * @return string
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return "" + random.nextInt(10000) + System.currentTimeMillis();
//return UUID.randomUUID().toString() + "_" + fileName;

	}

	/**
	 * 文件大小，返回kb.mb ?
	 * 
	 * @return
	 */
	private String getSize(File file) {
		String size = "";
		long fileLength = file.length();
		DecimalFormat df = new DecimalFormat("#.00");
		if (fileLength < 1024) {
			size = df.format((double) fileLength) + "BT";
		} else if (fileLength < 1048576) {
			size = df.format((double) fileLength / 1024) + "KB";
		} else if (fileLength < 1073741824) {
			size = df.format((double) fileLength / 1048576) + "MB";
		} else {
			size = df.format((double) fileLength / 1073741824) + "GB";
		}

		return size;

	}

}
