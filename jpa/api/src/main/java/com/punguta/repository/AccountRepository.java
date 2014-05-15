package com.punguta.repository;

import org.springframework.data.repository.CrudRepository;

import com.punguta.domain.account.Account;

/**
 * User: ruslan
 * Date: 5/15/14
 */
public interface AccountRepository extends CrudRepository<Account, Long>{
}
