package com.imedia24.imediaStore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imedia24.imediaStore.Model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

	  List<Product> findAllByCategoryId(Integer idCategory);

}