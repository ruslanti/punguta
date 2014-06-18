package com.punguta.jpa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.Account;
import com.punguta.jpa.domains.Transaction;

/**
 * User: ruslan
 * Date: 5/22/14
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t JOIN t.splits s WHERE s.account = ?1 AND t.posted > ?2")
    public List<Transaction> findByAccountSince(Account account, Date since);

    @Query("SELECT sum(s.value) FROM Transaction t JOIN t.splits s WHERE s.account = ?1")
    public Long findTotals(Account account);
}
