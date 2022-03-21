package com.fpoly.huongque.duantotnghiep.service.impl;

import com.fpoly.huongque.duantotnghiep.dao.CategoryDao;
import com.fpoly.huongque.duantotnghiep.dao.CategoryModelDao;
import com.fpoly.huongque.duantotnghiep.entity.Category;
import com.fpoly.huongque.duantotnghiep.model.CategoryModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	CategoryModelDao categoryModelDao;

	public <S extends Category> S save(S entity) {
		return categoryDao.save(entity);
	}

	public <S extends Category> Optional<S> findOne(Example<S> example) {
		return categoryDao.findOne(example);
	}

	public Page<Category> findAll(Pageable pageable) {
		return categoryDao.findAll(pageable);
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public List<Category> findAll(Sort sort) {
		return categoryDao.findAll(sort);
	}

	public List<Category> findAllById(Iterable<Integer> ids) {
		return categoryDao.findAllById(ids);
	}

	public Category findById(Integer id) {
		return categoryDao.findById(id).get();
	}

	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return categoryDao.saveAll(entities);
	}

	public void flush() {
		categoryDao.flush();
	}

	public <S extends Category> S saveAndFlush(S entity) {
		return categoryDao.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return categoryDao.existsById(id);
	}

	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryDao.saveAllAndFlush(entities);
	}

	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categoryDao.findAll(example, pageable);
	}

	public <S extends Category> long count(Example<S> example) {
		return categoryDao.count(example);
	}

	public <S extends Category> boolean exists(Example<S> example) {
		return categoryDao.exists(example);
	}

	public void deleteAllInBatch(Iterable<Category> entities) {
		categoryDao.deleteAllInBatch(entities);
	}

	public long count() {
		return categoryDao.count();
	}

	public void deleteById(Integer id) {
		categoryDao.deleteById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		categoryDao.deleteAllByIdInBatch(ids);
	}

	public void delete(Category entity) {
		categoryDao.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		categoryDao.deleteAllById(ids);
	}

	public void deleteAllInBatch() {
		categoryDao.deleteAllInBatch();
	}

	public void deleteAll(Iterable<? extends Category> entities) {
		categoryDao.deleteAll(entities);
	}

	public void deleteAll() {
		categoryDao.deleteAll();
	}

	public Category getById(Integer id) {
		return categoryDao.getById(id);
	}

	public <S extends Category> List<S> findAll(Example<S> example) {
		return categoryDao.findAll(example);
	}

	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		return categoryDao.findAll(example, sort);
	}

	@Override
	public Category create(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.save(category);
	}

	@Override //ne
	public List<CategoryModel> findAllandSl() {
		return categoryModelDao.findAllandSl();
	}
	

}
