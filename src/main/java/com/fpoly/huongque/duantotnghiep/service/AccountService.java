package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Account;

public interface AccountService {

	Account findByEmailIgnoreCase(String email);

	Account findByUserNameIgnoreCase(String username);

	Account findByPhoneIgnoreCase(String phone_Number);

	List<Account> findAll();

	Account findById(Integer id);

	void create(Account account);

	Account update(Account account);

	void delete(Integer id);

	List<Account> GetTop10AccountDay();

	List<Account> GetTop10AccountMonth();

	List<Account> GetTop10AccountYear();

	Account GetAccountByBill(Integer id);

}
