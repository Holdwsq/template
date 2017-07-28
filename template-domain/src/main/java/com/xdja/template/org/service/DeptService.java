package com.xdja.template.org.service;

import java.util.List;

import com.xdja.template.org.bean.Dept;

public interface DeptService {

	public List<Dept> tree();
	
	public boolean editSave(Dept dept);
	
	public int getPersonSizeOfDept(int deptId);
	
	public int getChildDeptSize(int deptId);
	
	public boolean delete(int deptId);
	
	public boolean add(Dept dept);
}
