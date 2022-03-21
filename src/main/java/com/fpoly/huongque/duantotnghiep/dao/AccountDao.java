package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountDao extends JpaRepository<Account, Integer> {

	@Query(value = "SELECT * FROM account WHERE email LIKE ?1", nativeQuery = true)
	Account findByEmailIgnoreCase(String email);

	@Query(value = "SELECT * FROM account WHERE phone_Number LIKE ?1", nativeQuery = true)
	Account findByPhoneIgnoreCase(String phone);

	@Query(value = "SELECT * FROM account WHERE user_Name LIKE ?1", nativeQuery = true)
	Account findByUserNameIgnoreCase(String user_Name);

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE','STAF')")
	List<Account> getAdministrators();

	@Query(value = "select account.* from account inner join Top10AccountDay\r\n"
			+ "on account.id_Account = Top10AccountDay.id_Account", nativeQuery = true)
	List<Account> GetTop10AccountDay();

	@Query(value = "select * from account inner join Top10AccountMonth\r\n"
			+ "on account.id_Account = Top10AccountMonth.id_Account", nativeQuery = true)
	List<Account> GetTop10AccountMonth();

	@Query(value = "select * from account inner join Top10AccountYear\r\n"
			+ "on account.id_Account = Top10AccountYear.id_Account", nativeQuery = true)
	List<Account> GetTop10AccountYear();

	@Query(value = "select account.* from  account inner join bill\r\n" + "on account.id_Account =  bill.id_Account\r\n"
			+ "where bill.id_Bill = ?", nativeQuery = true)
	Account GetAccountByBill(Integer id);

}