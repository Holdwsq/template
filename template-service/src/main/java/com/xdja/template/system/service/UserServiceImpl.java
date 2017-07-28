package com.xdja.template.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfaster.mango.plugin.page.Page;
import org.jfaster.mango.transaction.TransactionAction;
import org.jfaster.mango.transaction.TransactionStatus;
import org.jfaster.mango.transaction.TransactionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xdja.template.page.Pagination;
import com.xdja.template.system.bean.Function;
import com.xdja.template.system.bean.User;
import com.xdja.template.system.bean.UserRole;
import com.xdja.template.system.dao.FunctionDao;
import com.xdja.template.system.dao.UserDao;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FunctionDao funcDao;
	
	public User queryByAccountAndPwd(String account, String password) {
		User user = userDao.queryByAccountAndPwd(account, password);
		if(user != null) {
			//查询当前用户有权限的菜单
			List<String> permissions = funcDao.queryPermissionByUserId(user.getId());
			user.setPermissions(permissions);
		}
		return user;
	}

	public List<Function> queryFuncs(int userId) {
		//准备第一级菜单
		List<Function> roots = funcDao.queryFuncsByPid(0);
		Map<Integer, Function> rootMap = new HashMap<Integer, Function>();
		for(Function root : roots) {
			rootMap.put(root.getId(), root);
		}
		//查询用户的菜单
		List<Function> funcs = funcDao.queryFuncsByUserId(userId);
		
		Map<Integer, Function> funcMap = new HashMap<Integer, Function>();
		List<Function> result = new ArrayList<Function>();
		for(Function func : funcs) {
			if(func.getPid() == 0) {
				funcMap.put(func.getId(), func);
				result.add(func);
			} else {
				Function parent = funcMap.get(func.getPid());
				if(parent == null) {
					parent = rootMap.get(func.getPid());
					funcMap.put(func.getPid(), parent);
					result.add(parent);
				}
				List<Function> children = parent.getChild();
				if(children == null) {
					children = new ArrayList<Function>();
					parent.setChild(children);
				}
				children.add(func);
			}
		}
		return result;
	}

	public boolean isPwdEquals(int userId, String pwd) {
		User user = this.userDao.getUser(userId);
		if(user == null) {
			return false;
		}
		if(user.getPwd().equals(pwd)) {
			return true;
		}
		return false;
	}

	public void changePwd(int userId, String pwd) {
		this.userDao.changePwd(userId, pwd);
	}

	public Pagination queryUsers(int pageNum, int pageSize) {
		Page page = Page.create(pageNum, pageSize);
		
		Pagination pagination = new Pagination();
		pagination.setList(this.userDao.query(page));
		pagination.setTotal(page.getTotal());
		return pagination;
	}
	
	public boolean resetPwd(int userId, String pwd) {
		return this.userDao.updatePwd(userId, pwd);
	}
	
	public boolean disable(int userId) {
		return this.userDao.updateStatus(userId, User.STATUS_DISABLE);
	}
	
	public boolean enable(int userId) {
		return this.userDao.updateStatus(userId, User.STATUS_ENABLE);
	}
	
	public boolean add(final User user) {
		int exists = this.userDao.accountExist(user.getAccount());
		if(exists > 0) {
			return false;
		}
		TransactionTemplate.execute(new TransactionAction() {
			public void doInTransaction(TransactionStatus status) {
				int userId = userDao.addUser(user);
				List<UserRole> list = new ArrayList<UserRole>();
				for(Integer roleId : user.getRoles()) {
					UserRole ur = new UserRole(userId, roleId);
					list.add(ur);
				}
				userDao.saveUserRole(list);
			}
		});
		return true;
	}

	public List<Integer> queryUserRoles(int userId) {
		return this.userDao.queryUserRoles(userId);
	}

	public void edit(final User user) {
		TransactionTemplate.execute(new TransactionAction() {
			public void doInTransaction(TransactionStatus status) {
				userDao.updateUser(user);
				userDao.clearUserRoles(user.getId());
				List<UserRole> list = new ArrayList<UserRole>();
				for(Integer roleId : user.getRoles()) {
					UserRole ur = new UserRole(user.getId(), roleId);
					list.add(ur);
				}
				userDao.saveUserRole(list);
			}
		});
	}
}
