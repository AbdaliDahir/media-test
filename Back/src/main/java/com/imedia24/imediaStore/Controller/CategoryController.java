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
import com.imedia24.imediaStore.Services.CategoryService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/categories/")  
public class CategoryController {

	 private CategoryService categoryService;

	  @Autowired
	  public CategoryController(CategoryService categoryService) {
	    this.categoryService = categoryService;
	  }
 
	  @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public CategoryDto save(@RequestBody CategoryDto dto) {
	    return categoryService.save(dto);
	  }

	  @GetMapping(value = "{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public CategoryDto findById(@PathVariable("categoryId") Integer categoryId) {
	    return categoryService.findById(categoryId);
	  }


	  @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<CategoryDto> findAll() {
	    return categoryService.findAll();
	  }

	  @DeleteMapping(value = "delete/{categoryId}")
	  public void delete(@PathVariable("categoryId") Integer id) {
	    categoryService.delete(id);
	  }
	
}
