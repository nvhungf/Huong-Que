package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.LikeDao;
import com.fpoly.huongque.duantotnghiep.entity.Likes;
import com.fpoly.huongque.duantotnghiep.service.LikeService;


@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	LikeDao likeDao;

	@Override
	public List<Likes> findAll() {
		// TODO Auto-generated method stub
		return likeDao.findAll();
	}

	public <S extends Likes> S save(S entity) {
		return likeDao.save(entity);
	}

	public <S extends Likes> Optional<S> findOne(Example<S> example) {
		return likeDao.findOne(example);
	}

	public Page<Likes> findAll(Pageable pageable) {
		return likeDao.findAll(pageable);
	}

	public List<Likes> findAll(Sort sort) {
		return likeDao.findAll(sort);
	}

	public List<Likes> findAllById(Iterable<Integer> ids) {
		return likeDao.findAllById(ids);
	}

	public Optional<Likes> findById(Integer id) {
		return likeDao.findById(id);
	}

	public <S extends Likes> List<S> saveAll(Iterable<S> entities) {
		return likeDao.saveAll(entities);
	}

	public void flush() {
		likeDao.flush();
	}

	public <S extends Likes> S saveAndFlush(S entity) {
		return likeDao.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return likeDao.existsById(id);
	}

	public <S extends Likes> List<S> saveAllAndFlush(Iterable<S> entities) {
		return likeDao.saveAllAndFlush(entities);
	}

	public <S extends Likes> Page<S> findAll(Example<S> example, Pageable pageable) {
		return likeDao.findAll(example, pageable);
	}

	public <S extends Likes> long count(Example<S> example) {
		return likeDao.count(example);
	}

	public <S extends Likes> boolean exists(Example<S> example) {
		return likeDao.exists(example);
	}

	public void deleteAllInBatch(Iterable<Likes> entities) {
		likeDao.deleteAllInBatch(entities);
	}

	public long count() {
		return likeDao.count();
	}

	

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		likeDao.deleteAllByIdInBatch(ids);
	}

	public void delete(Likes entity) {
		likeDao.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		likeDao.deleteAllById(ids);
	}

	public void deleteAllInBatch() {
		likeDao.deleteAllInBatch();
	}


	public void deleteAll(Iterable<? extends Likes> entities) {
		likeDao.deleteAll(entities);
	}

	public void deleteAll() {
		likeDao.deleteAll();
	}

	public Likes getById(Integer id) {
		return likeDao.getById(id);
	}

	public <S extends Likes> List<S> findAll(Example<S> example) {
		return likeDao.findAll(example);
	}

	public <S extends Likes> List<S> findAll(Example<S> example, Sort sort) {
		return likeDao.findAll(example, sort);
	}

	@Override
	public Likes create(Likes likes) {
		// TODO Auto-generated method stub
		return likeDao.save(likes);
	}
	
	@Override
	public List<Likes> findByIdAccount(Integer idAccount) {
		return likeDao.findByIdAccount(idAccount);
	}
	
	@Override
	public void deleteById(Integer id) {
		likeDao.deleteById(id);
	}
	
}
