package com.xdja.template.org.service;

import org.jfaster.mango.plugin.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xdja.template.org.bean.Emploee;
import com.xdja.template.org.dao.EmploeeDao;
import com.xdja.template.page.Pagination;

@Repository
public class EmploeeServiceImpl implements EmploeeService {

	@Autowired
	private EmploeeDao dao;

	public Emploee queryEmploeeByEmplidAndPwd(String emplid, String password) {
		return this.dao.getEmploee(emplid, password);
	}
	
	public Pagination list(int pageNum, int pageSize) {
		Page page = Page.create(pageNum, pageSize);
		Pagination pagination = new Pagination();
		pagination.setList(this.dao.query(page));
		pagination.setTotal(page.getTotal());
		return pagination;
	}
	
}
