package com.xdja.template.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdja.template.org.service.EmploeeService;
import com.xdja.template.page.Pagination;

@RestController
@RequestMapping("/admin/orgemp")
public class EmploeeController {

	@Autowired
	private EmploeeService service;
	
	@RequestMapping("list")
	public Pagination list(int pageNum, int pageSize) {
		return this.service.list(pageNum, pageSize);
	}
}
