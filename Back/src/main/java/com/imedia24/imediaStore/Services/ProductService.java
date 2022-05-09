package com.imedia24.imediaStore.Services;

import java.util.List;

import com.imedia24.imediaStore.Dto.ProductDto;

public interface ProductService {
	
	ProductDto save(ProductDto dto);

	ProductDto findById(Integer id);

	List<ProductDto> findAll();

	List<ProductDto> findAllProductByIdCategory(Integer categoryId);

	void delete(Integer id);
}
