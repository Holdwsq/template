package com.xdja.template.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdja.template.httpbean.ResponseBean;

@RestController
@RequestMapping("api/pub")
public class UserLoginController {
	
	@RequestMapping("login")
	public ResponseBean login(String username, String password) {
		
		if(username.equals("zxq") && password.equals("111111")) {
			return ResponseBean.createSuccess("登录成功");
		}
		return ResponseBean.createError("登录失败");
	}

}
