package com.xdja.template.system.service;

import java.util.List;

import com.xdja.template.page.Pagination;
import com.xdja.template.system.bean.Function;
import com.xdja.template.system.bean.User;

public interface UserService {

	User queryByAccountAndPwd(String account, String password);
	
	List<Function> queryFuncs(int userId);
	
	boolean isPwdEquals(int userId, String pwd);
	
	void changePwd(int userId, String pwd);
	
	Pagination queryUsers(int pageNum, int PageSize);
	
	boolean resetPwd(int userId, String pwd);
	
	boolean disable(int userId);
	
	boolean enable(int userId);
	
	boolean add(User user); 
	
	List<Integer> queryUserRoles(int userId);

	void edit(User user);
}
