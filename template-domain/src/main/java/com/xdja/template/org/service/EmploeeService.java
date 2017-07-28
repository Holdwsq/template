package com.xdja.template.org.service;

import com.xdja.template.org.bean.Emploee;
import com.xdja.template.page.Pagination;

public interface EmploeeService {

	Emploee queryEmploeeByEmplidAndPwd(String emplid, String password);

	Pagination list(int pageNum, int pageSize);
}
