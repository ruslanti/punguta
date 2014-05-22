package com.punguta.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.User;

/**
 * User: ruslan
 * Date: 5/22/14
 */
public interface UserRepository extends CrudRepository<User, Long>{
}
