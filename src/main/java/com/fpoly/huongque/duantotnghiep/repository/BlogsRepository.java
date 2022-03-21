package com.fpoly.huongque.duantotnghiep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.huongque.duantotnghiep.entity.Blogs;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, Long>{

}
