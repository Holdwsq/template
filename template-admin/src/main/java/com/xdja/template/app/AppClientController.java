package com.xdja.template.app;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xdja.template.Constants;
import com.xdja.template.app.bean.AppClient;
import com.xdja.template.app.service.AppClientService;
import com.xdja.template.httpbean.ResponseBean;
import com.xdja.template.page.Pagination;


@RestController
@RequestMapping("/admin/appup")
public class AppClientController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppClientService service;
	
	@RequestMapping("list")
	public Pagination list(int pageNum, int pageSize) {
		return this.service.list(pageNum, pageSize);
	}
	
	@RequestMapping("del/{appId}")
	public void del(@PathVariable Integer appId) {
		this.service.delete(appId);
	}
	
	@RequestMapping(value = "add", consumes = "multipart/form-data", method = RequestMethod.POST)
    public ResponseBean add(@RequestParam("apkfile") CommonsMultipartFile apkfile, AppClient app, 
    		HttpServletRequest request) throws IllegalStateException, IOException{
        // 判断文件是否存在
        if (!apkfile.isEmpty()) {
        	String path = request.getSession().getServletContext().getRealPath("") 
        			+ "/" + Constants.APK_UPLOAD_PAT + "/" + 
        			app.getVersionCode() + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            path += apkfile.getOriginalFilename();
            log.info(path);
            
            File localFile = new File(path);
			apkfile.transferTo(localFile);
			
			app.setDate(System.currentTimeMillis());
			app.setUrl("/" + Constants.APK_UPLOAD_PAT + "/" + app.getVersionCode() + 
					"/" + apkfile.getOriginalFilename());
			service.save(app);
        }
        return ResponseBean.createError("文件为空，请重新选择");
    }
}
