package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.ProductDao;
import com.fpoly.huongque.duantotnghiep.dao.StatisticByProductDao;
import com.fpoly.huongque.duantotnghiep.entity.Product;
import com.fpoly.huongque.duantotnghiep.entity.StatisticByProduct;
import com.fpoly.huongque.duantotnghiep.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	

	@Override
	public List<Product> findTop5Trending() {
		return productDao.findTop5Trending();
	}



	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}
	
	

	@Override
	public List<Product> findByLikes(String id) {
		return productDao.findByLikes(id);
	}


	public <S extends Product> S save(S entity) {
		return productDao.save(entity);
	}

	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return productDao.findOne(example);
	}

	public Page<Product> findAll(Pageable pageable) {
		return productDao.findAll(pageable);
	}

	public List<Product> findAll(Sort sort) {
		return productDao.findAll(sort);
	}

	public List<Product> findAllById(Iterable<Integer> ids) {
		return productDao.findAllById(ids);
	}

	public Product findById(Integer id) {
		return productDao.findById(id).get();
	}

	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return productDao.saveAll(entities);
	}

	public void flush() {
		productDao.flush();
	}

	public <S extends Product> S saveAndFlush(S entity) {
		return productDao.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return productDao.existsById(id);
	}

	public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productDao.saveAllAndFlush(entities);
	}

	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productDao.findAll(example, pageable);
	}

	public <S extends Product> long count(Example<S> example) {
		return productDao.count(example);
	}

	public <S extends Product> boolean exists(Example<S> example) {
		return productDao.exists(example);
	}

	public void deleteAllInBatch(Iterable<Product> entities) {
		productDao.deleteAllInBatch(entities);
	}

	public long count() {
		return productDao.count();
	}

	public void deleteById(Integer id) {
		productDao.deleteById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		productDao.deleteAllByIdInBatch(ids);
	}

	public void delete(Product entity) {
		productDao.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		productDao.deleteAllById(ids);
	}

	public void deleteAllInBatch() {
		productDao.deleteAllInBatch();
	}

	public void deleteAll(Iterable<? extends Product> entities) {
		productDao.deleteAll(entities);
	}

	public void deleteAll() {
		productDao.deleteAll();
	}

	public Product getById(Integer id) {
		return productDao.getById(id);
	}

	public <S extends Product> List<S> findAll(Example<S> example) {
		return productDao.findAll(example);
	}

	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return productDao.findAll(example, sort);
	}

	@Override
	public Product create(Product product) {
		return productDao.save(product);

	}

	@Override
	public void delete(Integer id) {
		productDao.deleteById(id);

	}

	@Override
	public void update(Product product) {
		productDao.save(product);

	}

	@Override
	public List<Product> findTop10() {
		// TODO Auto-generated method stub
		return productDao.findTop10();
	}

	@Override
	public List<Product> search(String keyword) {
		if (keyword != null) {
			return productDao.search(keyword);
		}
		return productDao.findAll();
	}

	@Override
	public List<Product> findByCategory(String cid) {
		if (cid != null) {
			return productDao.findByCategory(cid);
		}
		return productDao.findAll();
	}

	@Override
	public List<Product> findByRegion(String rid) {
		if (rid != null) {
			return productDao.findByRegion(rid);
		}
		return productDao.findAll();
	}



	@Override
	public List<Product> getAllProductEnabled() {
		// TODO Auto-generated method stub
		return productDao.getAllProductEnabled();
	}

	

}
