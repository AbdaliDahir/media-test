package com.imedia24.imediaStore.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imedia24.imediaStore.Dto.ProductDto;
import com.imedia24.imediaStore.Exception.NotFoundException;
import com.imedia24.imediaStore.Exception.codeErrors;
import com.imedia24.imediaStore.Repository.ProductRepository;
import com.imedia24.imediaStore.Services.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
	  this.productRepository = productRepository;
	}
	
	
	@Override
	public ProductDto save(ProductDto dto) {
//	  List<String> errors = ProductValidator.validate(dto);
//	  if (!errors.isEmpty()) {
//	    log.error("Invalid Product {}", dto);
//	    throw new InvalidException("Invalid Product ", ErrorCodes.ARTICLE_NOT_VALID, errors);
//	  }

	  return ProductDto.fromEntity(
	      productRepository.save(
	          ProductDto.toEntity(dto)
	      )
	  );
	}

	@Override
	public ProductDto findById(Integer id) {
	  if (id == null) {
//	    log.error("Product ID null");
	    return null;
	  }

	  return productRepository.findById(id).map(ProductDto::fromEntity).orElseThrow(() ->
	      new NotFoundException("No Product Found With ID = " + id, codeErrors.PRODUCT_NOT_FOUND)
	  );
	}


	@Override
	public List<ProductDto> findAll() {
	  return productRepository.findAll().stream()
	      .map(ProductDto::fromEntity)
	      .collect(Collectors.toList());
	}


	@Override
	public List<ProductDto> findAllProductByIdCategory(Integer categoryId) {
	  return productRepository.findAllByCategoryId(categoryId).stream()
	      .map(ProductDto::fromEntity)
	      .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
	  if (id == null) {
//	    log.error("Product ID null");
	    return;
	  }
	  productRepository.deleteById(id);
	}
	
}
