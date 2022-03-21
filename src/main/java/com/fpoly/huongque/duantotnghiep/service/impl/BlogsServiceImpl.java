package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.entity.Blogs;
import com.fpoly.huongque.duantotnghiep.repository.BlogsRepository;
import com.fpoly.huongque.duantotnghiep.service.BlogsService;


@Service
public class BlogsServiceImpl implements BlogsService {
	BlogsRepository blogsRepository;

	public BlogsServiceImpl(BlogsRepository blogsRepository) {
		this.blogsRepository = blogsRepository;
	}

	@Override
	public <S extends Blogs> S save(S entity) {
		return blogsRepository.save(entity);
	}

	@Override
	public List<Blogs> findAll() {
		return blogsRepository.findAll();
	}

	@Override
	public List<Blogs> findAll(Sort sort) {
		return blogsRepository.findAll(sort);
	}

	@Override
	public Optional<Blogs> findById(Long id) {
		return blogsRepository.findById(id);
	}

	@Override
	public <S extends Blogs> List<S> saveAll(Iterable<S> entities) {
		return blogsRepository.saveAll(entities);
	}

	@Override
	public long count() {
		return blogsRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		blogsRepository.deleteById(id);
	}

	@Override
	public void delete(Blogs entity) {
		blogsRepository.delete(entity);
	}


	@Override
	public void deleteAll() {
		blogsRepository.deleteAll();
	}

	@Override
	public Blogs getById(Long id) {
		return blogsRepository.getById(id);
	}
	
	
}
