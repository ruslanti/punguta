package com.punguta.repository;

import org.springframework.data.repository.CrudRepository;

import com.punguta.domain.Book;

/**
 * User: ruslan
 * Date: 5/15/14
 */
public interface BookRepository extends CrudRepository<Book, Long>{
}
