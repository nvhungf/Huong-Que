package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.ImageDao;
import com.fpoly.huongque.duantotnghiep.entity.Image;
import com.fpoly.huongque.duantotnghiep.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageDao imageDao;

	public <S extends Image> S save(S entity) {
		return imageDao.save(entity);
	}

	public <S extends Image> Optional<S> findOne(Example<S> example) {
		return imageDao.findOne(example);
	}

	public Page<Image> findAll(Pageable pageable) {
		return imageDao.findAll(pageable);
	}

	public List<Image> findAll() {
		return imageDao.findAll();
	}

	public List<Image> findAll(Sort sort) {
		return imageDao.findAll(sort);
	}

	public List<Image> findAllById(Iterable<Integer> ids) {
		return imageDao.findAllById(ids);
	}

	public Optional<Image> findById(Integer id) {
		return imageDao.findById(id);
	}

	public <S extends Image> List<S> saveAll(Iterable<S> entities) {
		return imageDao.saveAll(entities);
	}

	public void flush() {
		imageDao.flush();
	}

	public <S extends Image> S saveAndFlush(S entity) {
		return imageDao.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return imageDao.existsById(id);
	}

	public <S extends Image> List<S> saveAllAndFlush(Iterable<S> entities) {
		return imageDao.saveAllAndFlush(entities);
	}

	public <S extends Image> Page<S> findAll(Example<S> example, Pageable pageable) {
		return imageDao.findAll(example, pageable);
	}

	public <S extends Image> long count(Example<S> example) {
		return imageDao.count(example);
	}

	public <S extends Image> boolean exists(Example<S> example) {
		return imageDao.exists(example);
	}

	public void deleteAllInBatch(Iterable<Image> entities) {
		imageDao.deleteAllInBatch(entities);
	}

	public long count() {
		return imageDao.count();
	}

	public void deleteById(Integer id) {
		imageDao.deleteById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		imageDao.deleteAllByIdInBatch(ids);
	}

	public void delete(Image entity) {
		imageDao.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		imageDao.deleteAllById(ids);
	}

	public void deleteAllInBatch() {
		imageDao.deleteAllInBatch();
	}

	public void deleteAll(Iterable<? extends Image> entities) {
		imageDao.deleteAll(entities);
	}

	public void deleteAll() {
		imageDao.deleteAll();
	}

	public Image getById(Integer id) {
		return imageDao.getById(id);
	}

	public <S extends Image> List<S> findAll(Example<S> example) {
		return imageDao.findAll(example);
	}

	public <S extends Image> List<S> findAll(Example<S> example, Sort sort) {
		return imageDao.findAll(example, sort);
	}

	@Override
	public List<Image> GetImageByProduct(Integer id_product) {
		// TODO Auto-generated method stub
		return imageDao.GetImageByProduct(id_product);
	}

	@Override
	public Image create(Image image) {
		// TODO Auto-generated method stub
		return imageDao.save(image);
	}

}
