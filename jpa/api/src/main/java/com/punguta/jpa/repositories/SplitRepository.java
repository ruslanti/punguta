package com.punguta.jpa.repositories;

import com.punguta.jpa.domains.Account;
import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.Split;

import java.util.Date;

/**
 * User: ruslan
 * Date: 5/22/14
 */
public interface SplitRepository extends CrudRepository<Split, Long>{
}
