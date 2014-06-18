package com.punguta.jpa.repositories;

import com.punguta.jpa.domains.User;
import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.Book;

/**
 * User: ruslan
 * Date: 5/15/14
 */
public interface BookRepository extends CrudRepository<Book, Long>{

    public Book findByUser(User user);
}
