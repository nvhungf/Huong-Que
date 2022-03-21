package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.Feedback;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedbackDao extends JpaRepository<Feedback, Integer> {
	
	@Query(value = "select * from feedback where id_Account = ?", nativeQuery = true)
	List<Feedback> findByIdAccount(Integer id_Account);
}
