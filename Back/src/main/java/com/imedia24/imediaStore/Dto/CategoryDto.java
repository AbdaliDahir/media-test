package com.imedia24.imediaStore.Dto;

import com.imedia24.imediaStore.Model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

	  private Integer id;
	  private String name;
	  private String description;
	  private Category parent;
	  
	  private List<CategoryDto> children;

	  @JsonIgnore
	  private List<ProductDto> products;

	  public static CategoryDto fromEntity(Category category) {
	    if (category == null) {
	      return null;
	      // TODO throw an exception
	    }

	    return CategoryDto.builder()
	    		.id(category.getId())
	    		.name(category.getName())
	    		.description(category.getDescription())
	    		.parent(category.getParent())
	    		.build();
	  }

	  public static Category toEntity(CategoryDto categoryDto) {
	    if (categoryDto == null) {
	      return null;
	      // TODO throw an exception
	    }

	    Category category = new Category();
	    category.setId(categoryDto.getId());
	    category.setName(categoryDto.getName());
	    category.setDescription(categoryDto.getDescription());
	    category.setParent(categoryDto.getParent());

	    return category;
	  }
}
