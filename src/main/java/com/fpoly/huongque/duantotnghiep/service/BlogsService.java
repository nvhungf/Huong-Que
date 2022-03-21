package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.fpoly.huongque.duantotnghiep.entity.Blogs;

public interface BlogsService {

	Blogs getById(Long id);

	void deleteAll();

	void delete(Blogs entity);

	void deleteById(Long id);

	long count();

	<S extends Blogs> List<S> saveAll(Iterable<S> entities);

	Optional<Blogs> findById(Long id);

	List<Blogs> findAll(Sort sort);

	List<Blogs> findAll();

	<S extends Blogs> S save(S entity);

}
