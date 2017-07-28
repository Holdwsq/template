package com.xdja.template.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xdja.template.system.bean.Function;
import com.xdja.template.system.dao.FunctionDao;

@Repository
public class FuncServiceImpl implements FuncService{

	@Autowired
	private FunctionDao dao;
	
	public List<Function> queryAll() {
		List<Function> funcs = dao.queryAll();
		Map<Integer, Function> funcMap = new HashMap<Integer, Function>();
		List<Function> result = new ArrayList<Function>();
		for(Function func : funcs) {
			Function parent = funcMap.get(func.getPid());
			if(parent == null) {
				funcMap.put(func.getId(), func);
				result.add(func);
			} else {
				List<Function> children = parent.getChild();
				if(children == null) {
					children = new ArrayList<Function>();
					parent.setChild(children);
				}
				children.add(func);
			}
		}
		return result;
	}

}
