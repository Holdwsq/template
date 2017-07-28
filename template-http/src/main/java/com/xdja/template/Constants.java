package com.xdja.template;

import com.xdja.template.util.ConfigUtil;

public class Constants {

	public static final String JWT_KEY = (String) ConfigUtil.getValue("JWT.KEY");
	
	public static final int JWT_ADMIN_EXPIRA = Integer.parseInt((String) ConfigUtil.getValue("JWT.ADMIN.EXPIRA"));
	
	public static final String MD5_SALT = (String) ConfigUtil.getValue("MD5.SALT");

	public static final String ADMIN_DEFAULT_PWD = (String) ConfigUtil.getValue("ADMIN.DEFAULT.PWD"); 
	
	public static final String APK_UPLOAD_PAT = (String) ConfigUtil.getValue("APK.UPLOAD.PATH");
}
