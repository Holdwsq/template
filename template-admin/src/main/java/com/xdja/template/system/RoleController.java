package com.xdja.template.system;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xdja.template.system.bean.Function;
import com.xdja.template.system.bean.Role;
import com.xdja.template.system.service.FuncService;
import com.xdja.template.system.service.RoleService;

@RestController
@RequestMapping("/admin/sysrole")
public class RoleController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleService service;
	
	@Autowired
	private FuncService funcService;
	
	@RequestMapping("list")
	public List<Role> list() {
		return service.list();
	}
	
	@RequestMapping("funcs")
	public List<Function> queryAllFunc() {
		return funcService.queryAll();
	}
	
	@RequestMapping("addsave")
	public void addSave(RoleBean roleBean) {
		Role role = new Role();
		role.setName(roleBean.getName());
		this.service.add(role, roleBean.getFuncs());
	}
	
	@RequestMapping("del/{id}")
	public void delRole(@PathVariable Integer id) {
		if(id != null) {
			this.service.del(id);			
		}
	}
	
	@RequestMapping("rolefunc/{id}")
	public List<Integer> getRoleFunc(@PathVariable Integer id) {
		if(id != null) {
			return this.service.getRoleFunc(id);
		}
		return null;
	}
	
	@RequestMapping("editsave")
	public void editSave(RoleBean roleBean) {
		Role role = new Role();
		role.setId(roleBean.getId());
		role.setName(roleBean.getName());
		this.service.update(role, roleBean.getFuncs());
	}
}
