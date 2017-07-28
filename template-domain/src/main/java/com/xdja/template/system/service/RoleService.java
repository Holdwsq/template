package com.xdja.template.system.service;

import java.util.List;

import com.xdja.template.system.bean.Role;

public interface RoleService {
	
	Role get(int id);

	void add(Role role, List<Integer> funcs);
	
	void update(Role role, List<Integer> funcs);
	
	void del(int id);
	
	void update(Role role);
	
	List<Role> list();
	
	List<Integer> getRoleFunc(int id);
}
