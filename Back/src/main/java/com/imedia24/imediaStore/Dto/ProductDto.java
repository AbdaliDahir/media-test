package com.imedia24.imediaStore.Dto;

import java.math.BigDecimal;

import com.imedia24.imediaStore.Model.Product;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ProductDto {
 
	private Integer id;
	private String name;
	private String description;
	private BigDecimal europrice;
	private BigDecimal dollarprice;
	private BigDecimal madprice;
	private String image;
	private CategoryDto category;


	  public static ProductDto fromEntity(Product product) {
	    if (product == null) {
	      return null;
	    }
	    return ProductDto.builder()
	        .id(product.getId())
	        .name(product.getName())
	        .description(product.getDescription())
	        .europrice(product.getEuroprice())
	        .dollarprice(product.getDollarprice())
	        .madprice(product.getMadprice())
	        .image(product.getImage())
	        .category(CategoryDto.fromEntity(product.getCategory()))
	        .build();
	  }

	  public static Product toEntity(ProductDto productDto) {
	    if (productDto == null) {
	      return null;
	    }
	    Product product = new Product();
	    product.setId(productDto.getId());
	    product.setName(productDto.getName());
	    product.setDescription(productDto.getDescription());
	    product.setEuroprice(productDto.getEuroprice());
	    product.setDollarprice(productDto.getDollarprice());
	    product.setMadprice(productDto.getMadprice());
	    product.setImage(productDto.getImage());
	    product.setCategory(CategoryDto.toEntity(productDto.getCategory()));
	    return product;
	  }
}
