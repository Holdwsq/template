package com.xdja.template.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xdja.template.Constants;
import com.xdja.template.httpbean.ResponseBean;
import com.xdja.template.system.bean.User;
import com.xdja.template.system.service.UserServiceImpl;
import com.xdja.template.util.DigestUtil;
import com.xdja.template.util.LoginUtil;

@RestController
@RequestMapping("/admin/anony")
public class IndexController {

	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ResponseBean index(HttpServletRequest request) {
		User user = LoginUtil.getLoginUser(request);
		IndexBean indexBean = new IndexBean(user.getName(), 
				userService.queryFuncs(user.getId()));
		return ResponseBean.createSuccess(indexBean);
	}
	
	@RequestMapping(value="changePwd")
	public ResponseBean changePwd(String oldPwd, String newPwd, 
			HttpServletRequest request) {
		User user = LoginUtil.getLoginUser(request);
		if(!this.userService.isPwdEquals(user.getId(), DigestUtil.MD5Digest(oldPwd, Constants.MD5_SALT))) {
			return ResponseBean.createError("原密码不正确，请确认");
		}
		this.userService.changePwd(user.getId(), DigestUtil.MD5Digest(newPwd, Constants.MD5_SALT));
		return ResponseBean.createSuccess("");
	}
	
}
