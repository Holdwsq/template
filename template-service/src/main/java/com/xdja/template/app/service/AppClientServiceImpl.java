package com.xdja.template.app.service;

import org.jfaster.mango.plugin.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xdja.template.app.bean.AppClient;
import com.xdja.template.app.dao.AppClientDao;
import com.xdja.template.page.Pagination;

@Repository
public class AppClientServiceImpl implements AppClientService {

	@Autowired
	private AppClientDao dao;
	
	public AppClient queryUpgrade(Integer versionCode) {
		return dao.queryUpgrade(versionCode, Page.create(1, 1));
	}

	public Pagination list(int pageNum, int pageSize) {
		Page page = Page.create(pageNum, pageSize);
		Pagination pagination = new Pagination();
		pagination.setList(this.dao.query(page));
		pagination.setTotal(page.getTotal());
		return pagination;
	}

	public boolean delete(int appId) {
		return dao.deleteAppClient(appId);
	}

	public void save(AppClient app) {
		dao.addAppClient(app);
	}
	
}
