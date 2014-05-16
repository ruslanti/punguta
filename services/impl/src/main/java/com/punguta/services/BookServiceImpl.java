package com.punguta.services;

import com.punguta.domains.Book;
import com.punguta.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createNewBook() {
        Book book = new Book();

        bookRepository.save(book);
    }
}
