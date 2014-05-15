package com.punguta.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.punguta.domain.Account;

/**
 * User: ruslan
 * Date: 5/15/14
 */
public interface AccountRepository extends CrudRepository<Account, Long>{
}
