package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.Likes;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikeDao extends JpaRepository<Likes, Integer> {
	
	@Query("SELECT p FROM Likes p WHERE p.idAccount = ?1")
	List<Likes> findByIdAccount(Integer idAccount);
	
}
