package com.punguta.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.Account;

/**
 * User: ruslan
 * Date: 5/15/14
 */
public interface AccountRepository extends CrudRepository<Account, Long>{
}
