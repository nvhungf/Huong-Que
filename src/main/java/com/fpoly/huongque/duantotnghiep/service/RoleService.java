package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Role;



public interface RoleService {

	public List<Role> findAll();


	public Role findById(String string);


}
