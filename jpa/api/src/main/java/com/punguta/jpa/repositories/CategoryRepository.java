package com.punguta.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.Category;

/**
 * User: ruslan
 * Date: 5/22/14
 */
public interface CategoryRepository extends CrudRepository<Category, Long>{
}
