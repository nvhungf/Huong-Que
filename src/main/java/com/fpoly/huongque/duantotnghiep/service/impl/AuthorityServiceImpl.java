package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.AccountDao;
import com.fpoly.huongque.duantotnghiep.dao.AuthorityDao;
import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.Authority;
import com.fpoly.huongque.duantotnghiep.service.AuthorityService;
import com.fpoly.huongque.duantotnghiep.service.RoleService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDao authorityDAO;
	@Autowired
	AccountDao accountDao;
	@Autowired
	RoleService roleService;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountDao.getAdministrators();
		return authorityDAO.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return authorityDAO.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return authorityDAO.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authorityDAO.deleteById(id);
	}

	@Override
	public List<Authority> getAuth(Integer id) {
		// TODO Auto-generated method stub
		return authorityDAO.getAuth(id);
	}

}
