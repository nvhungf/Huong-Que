package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.Authority;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);

	@Query(value = "select * from authority where id_Account = ?", nativeQuery = true)
	List<Authority> getAuth(Integer id);
}
