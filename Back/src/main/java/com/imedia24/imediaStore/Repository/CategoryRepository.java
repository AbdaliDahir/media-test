package com.imedia24.imediaStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imedia24.imediaStore.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
