package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.RoleDao;
import com.fpoly.huongque.duantotnghiep.entity.Role;
import com.fpoly.huongque.duantotnghiep.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDao roleDao;

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role findById(String string) {
		// TODO Auto-generated method stub
		return roleDao.getById(string);
	}

}
