package com.punguta.jpa.repositories;

import javax.persistence.EntityManager;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.punguta.jpa.domains.config.JPAConfiguration;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AccountRepositoryTest {

    @Autowired
    EntityManager manager;

    @Test
    @Ignore
    public void thatAccount() throws Exception {
/*        assertTableExists(manager, "NOODLE_ORDERS");
        assertTableExists(manager, "ORDER_ORDER_ITEMS");

        assertTableHasColumn(manager, "NOODLE_ORDERS", "ORDER_ID");
        assertTableHasColumn(manager, "NOODLE_ORDERS", "SUBMISSION_DATETIME");*/
    }
}
