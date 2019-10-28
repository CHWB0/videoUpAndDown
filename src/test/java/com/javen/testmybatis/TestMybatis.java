/*package com.javen.testmybatis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.javen.model.Admin;
import com.javen.model.FileEntity;
import com.javen.model.User;
import com.javen.model.UvRelation;
import com.javen.service.AdminService;
import com.javen.service.FileService;
import com.javen.service.UserService;
import com.javen.service.UvRelationService;
  
@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class TestMybatis {  
    private static Logger logger = Logger.getLogger(TestMybatis.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private UserService userService = null;
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
        User user = userService.getUserByuNo("1111");
        String userName = user.getUserName();
        String password = user.getPassword();
        int uType = user.getuType();
        System.out.println("uNo="+user.getuNo()+"===userName="+userName+"====passwored="+password+"===uType="+uType);
        // System.out.println(user.getUserName());  
        // logger.info("ֵ��"+user.getUserName());  
        logger.info(JSON.toJSONString(user));  
    } 
    @Test
    public void test2(){
    	List<User> user =  userService.getUserByUserName("张三");
    	for (User user2 : user) {
    		logger.info(JSON.toJSONString(user2));
		}
    }
    @Test
    public void test3(){
    	List<User> user =  userService.getUserAll();
    	for (User user2 : user) {
    		System.out.println("userName="+user2.getUserName()+"====passwored="+user2.getPassword()+"===uType="+user2.getuType());
    		logger.info(JSON.toJSONString(user2));
		}
    }
    @Test
    public void test4(){
    	User user = new User("1111","bob","2136551",2) ;
//    	user.setId(5);
//    	user.setUsername("陈六");
//    	user.setPassword("33333");
//    	user.setUtype(2);
    	userService.insertUser(user);
    	System.out.println(user);
    	List<User> user0 =  userService.getUserAll();
    	for (User user2 : user0) {
    		System.out.println("uNo="+user2.getuNo()+"===userName="+user2.getUserName()+"====passwored="+user2.getPassword()+"===uType="+user2.getuType());
		}
    }
    @Test
    public void test5(){
    	userService.deleteUserById(1);
    	List<User> user =  userService.getUserAll();
    	for (User user2 : user) {
    		System.out.println("userName="+user2.getUserName()+"====passwored="+user2.getPassword()+"===uType="+user2.getuType());
    		logger.info(JSON.toJSONString(user2));
		}
    }
    @Test
    public void test6(){
    	User user = new User("2222","ss","3435",3) ;
    	user.setuId(5);
    	userService.updateUserById(user);
    	System.out.println(user);
    	List<User> user0 =  userService.getUserAll();
    	for (User user2 : user0) {
    		System.out.println("userName="+user2.getUserName()+"====passwored="+user2.getPassword()+"===uType="+user2.getuType());
		}
    }
    
    @Resource
    private AdminService adminService = null;
    @Test
    public void testa1(){
    	Admin admin = adminService.getAdminByNo(2019);
    	System.out.println("aNo:"+admin.getaNo()+"===adminName:"+admin.getAdminName()+"====aPasswored:"+admin.getaPassword()+"===aType:"+admin.getaType());
    	logger.info(JSON.toJSONString(admin));
    }
    @Test
    public void testa2(){
    	adminService.insertAdmin(new Admin(2019,"李四","77777","管理员"));
    }
    @Test
    public void testa3(){
    	List<Admin> admin = adminService.getAdminAll();
    	for (Admin admins : admin) {
    		logger.info(JSON.toJSONString(admins));
		}
    }
    @Test
    public void testa4(){
    	adminService.deleteAdminByNo(2019);
    	List<Admin> admin = adminService.getAdminAll();
    	for (Admin admins : admin) {
    		logger.info(JSON.toJSONString(admins));
		}
    }
    @Test
    public void testa5(){
    	adminService.updateAdminByNo(new Admin(2019,"张三","838487","管理员"));
    }
    
    @Resource
    private FileService fileService = null;
    @Test
    public void testf1(){
    	fileService.deleteFileById(38);
    }
    @Test
    public void testf2(){
    	List<FileEntity> fileEntitie = fileService.getFileByFileName("kZjwNN968CXi1510753092_10s");
    	for (FileEntity fileEntitys : fileEntitie) {
    		logger.info(JSON.toJSONString(fileEntitys));
		}
    }
    @Test
    public void testf3(){
    	List<FileEntity> fileEntities = fileService.getFileByFileType(".mp4");
    	for (FileEntity fileEntity : fileEntities) {
    		logger.info(JSON.toJSONString(fileEntity));
		}
    }
    @Test 
    public void testf4(){
    	List<FileEntity> fileEntities = fileService.getFileByFileUploadTime("2019-09-21");
    	for (FileEntity fileEntity : fileEntities) {
    		logger.info(JSON.toJSONString(fileEntity));
    		System.out.println("titleOrig"+fileEntity.getTitleOrig());
		}
    }
    @Test 
    public void testf5(){
    	List<Map<String, List<Integer>>> listMap = fileService.getGroupFileByDate();
    	Map<String, List<Integer>> map = listMap.get(3);
    	System.out.println(map.get("uploadTime"));
    	
    	System.out.println(map.get("GROUP_CONCAT(fileId)"));
    	System.out.println(map);
//    	for (Map<String, Integer> map : listMap) {
//			System.out.println(map.get("uploadTime"));
//			System.out.println(map.get("GROUP_CONCAT(fileId)"));
//		}
    }
    @Test 
    public void testf6(){
    	List<FileEntity> fileEntities = fileService.getFileUploadTime();
    	for (FileEntity fileEntity : fileEntities) {
			System.out.println(fileEntity);
		}
    }
    @Autowired
    UvRelationService uvRelationService;
    @Test 
    public void testr1(){
    	UvRelation uvRelation = new UvRelation();
    	uvRelation.setuId(65);
    	uvRelation.setFileId(82);
    	uvRelationService.insertUvRelation(uvRelation);
    }
    @Test 
    public void testr2(){
    	List<Integer> fileIds = uvRelationService.getVideoIdByUserHistory(65);
    	for (Integer integer : fileIds) {
    		logger.info(JSON.toJSONString(integer));
		}
    	
    }
}
*/