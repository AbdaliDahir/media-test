package com.imedia24.imediaStore.Services;

import java.util.List;

import com.imedia24.imediaStore.Dto.CategoryDto;

public interface CategoryService {
	
	CategoryDto save(CategoryDto dto);

	CategoryDto findById(Integer id);
 
	List<CategoryDto> findAll();
	

	void delete(Integer id);
	  
}
