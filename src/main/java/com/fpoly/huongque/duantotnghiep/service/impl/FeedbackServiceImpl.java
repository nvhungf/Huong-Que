package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.FeedbackDao;
import com.fpoly.huongque.duantotnghiep.entity.Feedback;
import com.fpoly.huongque.duantotnghiep.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDao feedbackDao;

	

	public <S extends Feedback> Optional<S> findOne(Example<S> example) {
		return feedbackDao.findOne(example);
	}

	public Page<Feedback> findAll(Pageable pageable) {
		return feedbackDao.findAll(pageable);
	}

	public List<Feedback> findAll() {
		return feedbackDao.findAll();
	}

	public List<Feedback> findAll(Sort sort) {
		return feedbackDao.findAll(sort);
	}

	public List<Feedback> findAllById(Iterable<Integer> ids) {
		return feedbackDao.findAllById(ids);
	}

	public Optional<Feedback> findById(Integer id) {
		return feedbackDao.findById(id);
	}

	public <S extends Feedback> List<S> saveAll(Iterable<S> entities) {
		return feedbackDao.saveAll(entities);
	}

	public void flush() {
		feedbackDao.flush();
	}

	public <S extends Feedback> S saveAndFlush(S entity) {
		return feedbackDao.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return feedbackDao.existsById(id);
	}

	public <S extends Feedback> List<S> saveAllAndFlush(Iterable<S> entities) {
		return feedbackDao.saveAllAndFlush(entities);
	}

	public <S extends Feedback> Page<S> findAll(Example<S> example, Pageable pageable) {
		return feedbackDao.findAll(example, pageable);
	}

	public <S extends Feedback> long count(Example<S> example) {
		return feedbackDao.count(example);
	}

	public <S extends Feedback> boolean exists(Example<S> example) {
		return feedbackDao.exists(example);
	}

	public void deleteAllInBatch(Iterable<Feedback> entities) {
		feedbackDao.deleteAllInBatch(entities);
	}

	public long count() {
		return feedbackDao.count();
	}

	public void deleteById(Integer id) {
		feedbackDao.deleteById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		feedbackDao.deleteAllByIdInBatch(ids);
	}

	public void delete(Feedback entity) {
		feedbackDao.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		feedbackDao.deleteAllById(ids);
	}

	public void deleteAllInBatch() {
		feedbackDao.deleteAllInBatch();
	}

	public void deleteAll(Iterable<? extends Feedback> entities) {
		feedbackDao.deleteAll(entities);
	}

	public void deleteAll() {
		feedbackDao.deleteAll();
	}

	public Feedback getById(Integer id) {
		return feedbackDao.getById(id);
	}

	public <S extends Feedback> List<S> findAll(Example<S> example) {
		return feedbackDao.findAll(example);
	}

	public <S extends Feedback> List<S> findAll(Example<S> example, Sort sort) {
		return feedbackDao.findAll(example, sort);
	}
	
	@Override
	public <S extends Feedback> S save(S entity) {
		return feedbackDao.save(entity);
	}
	
	@Override
	public List<Feedback> findByIdAccount(Integer idAcount) {
		return feedbackDao.findByIdAccount(idAcount);
	}

}
