package com.xdja.template.app.service;

import com.xdja.template.app.bean.AppClient;
import com.xdja.template.page.Pagination;

public interface AppClientService {

	AppClient queryUpgrade(Integer versionCode);
	
	Pagination list(int pageNum, int pageSize);

	boolean delete(int appId);
	
	void save(AppClient app);
}
