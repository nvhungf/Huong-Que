package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Image;

public interface ImageService {

	List<Image> findAll();

	List<Image> GetImageByProduct(Integer id_product);

	Image create(Image image);

	void deleteById(Integer id);

}
