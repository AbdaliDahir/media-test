package com.imedia24.imediaStore.Services.Impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imedia24.imediaStore.Dto.CategoryDto;
import com.imedia24.imediaStore.Exception.NotFoundException;
import com.imedia24.imediaStore.Exception.codeErrors;
import com.imedia24.imediaStore.Model.Product;
import com.imedia24.imediaStore.Repository.CategoryRepository;
import com.imedia24.imediaStore.Repository.ProductRepository;
import com.imedia24.imediaStore.Services.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
	  this.categoryRepository = categoryRepository;
	  this.productRepository = productRepository;
	}

	@Override
	public CategoryDto save(CategoryDto dto) {
		// validation
//	    List<String> errors = CategoryValidator.validate(dto);
//	    if (!errors.isEmpty()) {
//	      log.error("Article is not valid {}", dto);
//	      throw new InvalidException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
//	    }
	  return CategoryDto.fromEntity(
	      categoryRepository.save(CategoryDto.toEntity(dto))
	  );
	}

	  @Override
	  public CategoryDto findById(Integer id) {
	    if (id == null) {
//	      log.error("Category ID is null");
	      return null;
	    }
	    return categoryRepository.findById(id)
	        .map(CategoryDto::fromEntity)
	        .orElseThrow(() -> new NotFoundException("There is NO Category With ID = " + id));
	  }
	 

	  @Override
	  public List<CategoryDto> findAll() {
	    return categoryRepository.findAll().stream()
	        .map(CategoryDto::fromEntity)
	        .collect(Collectors.toList());
	    
	  }
	  
	  
	  @Override
	  public void delete(Integer id) {
	    if (id == null) {
//	      log.error("Category ID is null");
	      return;
	    }
	    List<Product> products = productRepository.findAllByCategoryId(id);
	    // maybe check if category already have items before remove -- throw error
	    categoryRepository.deleteById(id);
	  }

	  

}
