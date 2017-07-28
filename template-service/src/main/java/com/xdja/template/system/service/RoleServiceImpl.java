package com.xdja.template.system.service;

import java.util.ArrayList;
import java.util.List;

import org.jfaster.mango.transaction.TransactionAction;
import org.jfaster.mango.transaction.TransactionStatus;
import org.jfaster.mango.transaction.TransactionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xdja.template.system.bean.Role;
import com.xdja.template.system.bean.RoleFunc;
import com.xdja.template.system.dao.RoleDao;

@Repository
public class RoleServiceImpl implements RoleService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleDao dao;
	
	public void add(final Role role, final List<Integer> funcs) {
		TransactionTemplate.execute(new TransactionAction() {
			public void doInTransaction(TransactionStatus status) {
				int roleId = dao.addRole(role);
				List<RoleFunc> list = new ArrayList<RoleFunc>();
				for(Integer funcId : funcs) {
					RoleFunc rf = new RoleFunc(roleId, funcId);
					list.add(rf);
				}
				dao.addRoleFuncs(list);
			}
		});
	}
	
	public void update(final Role role, final List<Integer> funcs) {
		TransactionTemplate.execute(new TransactionAction() {
			public void doInTransaction(TransactionStatus status) {
				dao.updateRole(role);
				dao.deleteRoleFunc(role.getId());
				List<RoleFunc> list = new ArrayList<RoleFunc>();
				for(Integer funcId : funcs) {
					RoleFunc rf = new RoleFunc(role.getId(), funcId);
					list.add(rf);
				}
				dao.addRoleFuncs(list);
			}
		});
	}

	public void del(final int id) {
		TransactionTemplate.execute(new TransactionAction() {
			public void doInTransaction(TransactionStatus status) {
				dao.deleteRoleFunc(id);
				dao.deleteRole(id);
			}
		});
	}
	
	public List<Integer> getRoleFunc(int id) {
		return dao.getRoleFunc(id);
	}

	public void update(Role role) {
		this.dao.updateRole(role);
	}

	public Role get(int id) {
		return this.dao.getRole(id);
	}

	public List<Role> list() {
		return this.dao.list();
	}
}
