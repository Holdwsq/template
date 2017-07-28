package com.xdja.template.upgrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdja.template.app.bean.AppClient;
import com.xdja.template.app.service.AppClientService;

@RestController
@RequestMapping("api/pub")
public class UpgradeController {

	@Autowired
	private AppClientService service;
	
	@RequestMapping("upgrade/{version}")
	public AppClient upgrade(@PathVariable Integer version) {
		return service.queryUpgrade(version);
	}
}
