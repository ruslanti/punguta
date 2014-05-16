package com.punguta.repositories;

import org.springframework.data.repository.CrudRepository;

import com.punguta.domains.Account;

/**
 * User: ruslan
 * Date: 5/15/14
 */
public interface AccountRepository extends CrudRepository<Account, Long>{
}
