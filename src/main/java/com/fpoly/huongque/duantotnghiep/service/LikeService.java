package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Likes;

public interface LikeService {

	List<Likes> findAll();

	Likes create(Likes likes);

	List<Likes> findByIdAccount(Integer idAccount);

	void deleteById(Integer id);

}
