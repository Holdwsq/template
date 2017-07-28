package com.xdja.template.util;

import javax.servlet.http.HttpServletRequest;

import com.xdja.template.system.bean.User;

public class LoginUtil {

	public static User getLoginUser(HttpServletRequest request) {
		Object obj = request.getAttribute("loginUser");
		if(obj == null) {
			return null;
		}
		return (User)obj; 
	}
}
