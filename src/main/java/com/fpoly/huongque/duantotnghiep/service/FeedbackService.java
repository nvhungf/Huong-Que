package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Feedback;

public interface FeedbackService {

	List<Feedback> findAll();

	<S extends Feedback> S save(S entity);

	List<Feedback> findByIdAccount(Integer idAcount);

}
