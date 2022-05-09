package com.imedia24.imediaStore.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import com.imedia24.imediaStore.Dto.CategoryDto;
import com.imedia24.imediaStore.Dto.ProductDto;
import com.imedia24.imediaStore.Services.CategoryService;
import com.imedia24.imediaStore.Services.ProductService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/products/")  
public class ProductController {
 
	private ProductService productService;

	  @Autowired
	  public ProductController(ProductService productService) {
	    this.productService = productService;
	  }


	  @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ProductDto save(@RequestBody ProductDto dto) {
	    return productService.save(dto);
	  }

	  @GetMapping(value = "{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ProductDto findById(@PathVariable("categoryId") Integer categoryId) {
	    return productService.findById(categoryId);
	  }

	  @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<ProductDto> findAll() {
	    return productService.findAll();
	  }
 
	  @GetMapping(value = "category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<ProductDto> findAllArticleByIdCategory(@PathVariable("categoryId") Integer categoryId) {
	    return productService.findAllProductByIdCategory(categoryId);
	  }

	  @DeleteMapping(value = "delete/{productId}")
	  public void delete(@PathVariable("productId") Integer id) {
		  productService.delete(id);
	  }
	  
}
