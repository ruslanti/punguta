package com.punguta.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.Transaction;

/**
 * User: ruslan
 * Date: 5/22/14
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
