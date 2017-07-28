package com.xdja.template;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdja.template.httpbean.ResponseBean;

@RestController
@RequestMapping("api/")
public class TestController {

	@RequestMapping("test")
	public ResponseBean test() {
		return ResponseBean.createSuccess("测试");
	}
}
