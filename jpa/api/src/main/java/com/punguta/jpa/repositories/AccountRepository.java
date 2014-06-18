package com.punguta.jpa.repositories;

import com.punguta.jpa.domains.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.Account;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * User: ruslan
 * Date: 5/15/14
 */
public interface AccountRepository extends CrudRepository<Account, Long>{

    @Query("SELECT account.id, SUM(split.value) FROM Split split JOIN split.account account WHERE account.id IN ?1 GROUP BY account")
    Map<Long, Long> findTotalsForAccountIds(Set<Long> ids);
}
