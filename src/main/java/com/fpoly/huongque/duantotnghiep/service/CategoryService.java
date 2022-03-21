package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fpoly.huongque.duantotnghiep.entity.Category;
import com.fpoly.huongque.duantotnghiep.model.CategoryModel;

public interface CategoryService {

	List<Category> findAll();

	Category findById(Integer id);

	Category create(Category category);

	void deleteById(Integer id);

	List<CategoryModel> findAllandSl(); // ne

}
