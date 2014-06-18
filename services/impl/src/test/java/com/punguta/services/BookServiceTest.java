package com.punguta.services;

import com.punguta.jpa.domains.Book;
import com.punguta.jpa.domains.Currency;
import com.punguta.jpa.domains.User;
import com.punguta.jpa.repositories.CommodityRepository;
import com.punguta.jpa.repositories.UserRepository;
import com.punguta.services.config.ServicesConfiguration;
import com.punguta.services.events.details.TotalDetails;
import com.punguta.services.exceptions.PungutaException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static com.punguta.services.ServicesFixture.generateUser;

/**
 * Created by ruslan on 6/18/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesConfiguration.class})
public class BookServiceTest {
    @Autowired
    BookService bookService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommodityRepository commodityRepository;

    private Book book;

    @Before
    public void before() throws PungutaException, IOException {
        User user = generateUser();
        userRepository.save(user);
        User.setCurrent(user);
        book = bookService.register(user, (Currency) commodityRepository.findByCode("USD"));
    }

    @Test
    public void testTotals() {
        TotalDetails totalDetails = bookService.findTotals();
    }
}
