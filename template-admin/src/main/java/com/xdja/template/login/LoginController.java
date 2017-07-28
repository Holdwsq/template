package com.xdja.template.login;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xdja.template.Constants;
import com.xdja.template.httpbean.ResponseBean;
import com.xdja.template.system.bean.User;
import com.xdja.template.system.service.UserServiceImpl;
import com.xdja.template.util.DigestUtil;

@RestController
@RequestMapping("/admin/public")
public class LoginController {
	
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseBean login(String username, String password) {
		
		User user = userService.queryByAccountAndPwd(username, 
				DigestUtil.MD5Digest(password, Constants.MD5_SALT));
		if(user != null) {
			//以下是json web token的操作
			user.setAccount(null);
			user.setCreateTime(0);
			user.setPwd(null);
			user.setStatus(0);
			user.setType(0);
			
			String compactJws = Jwts.builder()
					.setIssuedAt(new Date())
					.setId(user.getId() + "")
					.setSubject(user.getName())
					.claim("perm", user.getPermissions())
					.setExpiration(new Date(System.currentTimeMillis() + Constants.JWT_ADMIN_EXPIRA * 60 * 1000))
					.compressWith(CompressionCodecs.DEFLATE)
					.signWith(SignatureAlgorithm.HS256, Constants.JWT_KEY)
					.compact();
			
			return ResponseBean.createSuccess(compactJws);
		}
		return ResponseBean.createError("用户名或密码错误");
	}
}
