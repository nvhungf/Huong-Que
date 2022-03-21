package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.RegionsDao;
import com.fpoly.huongque.duantotnghiep.entity.Regions;
import com.fpoly.huongque.duantotnghiep.service.RegionsService;

@Service
public class RegionsServiceImpl implements RegionsService {
	@Autowired
	RegionsDao regionsDao;

	public <S extends Regions> S save(S entity) {
		return regionsDao.save(entity);
	}

	public <S extends Regions> Optional<S> findOne(Example<S> example) {
		return regionsDao.findOne(example);
	}

	public Page<Regions> findAll(Pageable pageable) {
		return regionsDao.findAll(pageable);
	}

	public List<Regions> findAll() {
		return regionsDao.findAll();
	}

	public List<Regions> findAll(Sort sort) {
		return regionsDao.findAll(sort);
	}

	public List<Regions> findAllById(Iterable<Integer> ids) {
		return regionsDao.findAllById(ids);
	}

	public Regions findById(Integer id) {
		return regionsDao.findById(id).get();
	}

	public <S extends Regions> List<S> saveAll(Iterable<S> entities) {
		return regionsDao.saveAll(entities);
	}

	public void flush() {
		regionsDao.flush();
	}

	public <S extends Regions> S saveAndFlush(S entity) {
		return regionsDao.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return regionsDao.existsById(id);
	}

	public <S extends Regions> List<S> saveAllAndFlush(Iterable<S> entities) {
		return regionsDao.saveAllAndFlush(entities);
	}

	public <S extends Regions> Page<S> findAll(Example<S> example, Pageable pageable) {
		return regionsDao.findAll(example, pageable);
	}

	public <S extends Regions> long count(Example<S> example) {
		return regionsDao.count(example);
	}

	public <S extends Regions> boolean exists(Example<S> example) {
		return regionsDao.exists(example);
	}

	public void deleteAllInBatch(Iterable<Regions> entities) {
		regionsDao.deleteAllInBatch(entities);
	}

	public long count() {
		return regionsDao.count();
	}

	public void deleteById(Integer id) {
		regionsDao.deleteById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		regionsDao.deleteAllByIdInBatch(ids);
	}

	public void delete(Regions entity) {
		regionsDao.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		regionsDao.deleteAllById(ids);
	}

	public void deleteAllInBatch() {
		regionsDao.deleteAllInBatch();
	}

	public void deleteAll(Iterable<? extends Regions> entities) {
		regionsDao.deleteAll(entities);
	}

	public void deleteAll() {
		regionsDao.deleteAll();
	}

	public Regions getById(Integer id) {
		return regionsDao.getById(id);
	}

	public <S extends Regions> List<S> findAll(Example<S> example) {
		return regionsDao.findAll(example);
	}

	public <S extends Regions> List<S> findAll(Example<S> example, Sort sort) {
		return regionsDao.findAll(example, sort);
	}

	@Override
	public Regions create(Regions regions) {
		// TODO Auto-generated method stub
		return regionsDao.save(regions);
	}

}
