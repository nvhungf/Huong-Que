package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.AccountDao;
import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDao accountDao;
	public Account findByEmailIgnoreCase(String email) {
		return accountDao.findByEmailIgnoreCase(email);
	}

	public Account findByPhoneIgnoreCase(String phone) {
		return accountDao.findByPhoneIgnoreCase(phone);
	}

	public Account findByUserNameIgnoreCase(String user_Name) {
		return accountDao.findByUserNameIgnoreCase(user_Name);
	}

	public <S extends Account> S save(S entity) {
		return accountDao.save(entity);
	}

	public <S extends Account> Optional<S> findOne(Example<S> example) {
		return accountDao.findOne(example);
	}

	public Page<Account> findAll(Pageable pageable) {
		return accountDao.findAll(pageable);
	}

	public List<Account> findAll() {
		return accountDao.findAll();
	}

	public List<Account> findAll(Sort sort) {
		return accountDao.findAll(sort);
	}

	public List<Account> findAllById(Iterable<Integer> ids) {
		return accountDao.findAllById(ids);
	}

	public Account findById(Integer id) {
		return accountDao.findById(id).get();
	}

	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
		return accountDao.saveAll(entities);
	}

	public void flush() {
		accountDao.flush();
	}

	public <S extends Account> S saveAndFlush(S entity) {
		return accountDao.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return accountDao.existsById(id);
	}

	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
		return accountDao.saveAllAndFlush(entities);
	}

	public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
		return accountDao.findAll(example, pageable);
	}

	public <S extends Account> long count(Example<S> example) {
		return accountDao.count(example);
	}

	public <S extends Account> boolean exists(Example<S> example) {
		return accountDao.exists(example);
	}

	public void deleteAllInBatch(Iterable<Account> entities) {
		accountDao.deleteAllInBatch(entities);
	}

	public long count() {
		return accountDao.count();
	}

	public void deleteById(Integer id) {
		accountDao.deleteById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		accountDao.deleteAllByIdInBatch(ids);
	}

	public void delete(Account entity) {
		accountDao.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		accountDao.deleteAllById(ids);
	}

	public void deleteAllInBatch() {
		accountDao.deleteAllInBatch();
	}

	public void deleteAll(Iterable<? extends Account> entities) {
		accountDao.deleteAll(entities);
	}

	public void deleteAll() {
		accountDao.deleteAll();
	}

	public Account getById(Integer id) {
		return accountDao.getById(id);
	}

	public <S extends Account> List<S> findAll(Example<S> example) {
		return accountDao.findAll(example);
	}

	public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
		return accountDao.findAll(example, sort);
	}

	@Override
	public void create(Account account) {
		accountDao.save(account);
	}

	@Override
	public Account update(Account account) {
		// TODO Auto-generated method stub
		return accountDao.save(account);
	}

	@Override
	public void delete(Integer id) {
		accountDao.deleteById(id);

	}

	@Override
	public List<Account> GetTop10AccountDay() {
		// TODO Auto-generated method stub
		return accountDao.GetTop10AccountDay();
	}

	@Override
	public List<Account> GetTop10AccountMonth() {
		return accountDao.GetTop10AccountMonth();
	}

	@Override
	public List<Account> GetTop10AccountYear() {
		return accountDao.GetTop10AccountYear();
	}

	@Override
	public Account GetAccountByBill(Integer id) {
		// TODO Auto-generated method stub
		return accountDao.GetAccountByBill(id);
	}

}
