package com.fpoly.huongque.duantotnghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.huongque.duantotnghiep.entity.StatisticByAccount;

public interface StatisticByAccountDao extends JpaRepository<StatisticByAccount, Integer> {
	@Query(value = "select * from StatisticByAccount where id_Account = ?", nativeQuery = true)
	StatisticByAccount getStatisticByAccount(Integer id_Account);
}
