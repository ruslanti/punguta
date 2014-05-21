package com.punguta.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.punguta.jpa.domains.Commodity;

/**
 * Created by ruslanti on 16.05.2014.
 */
public interface CommodityRepository extends CrudRepository<Commodity, Long> {

    public Commodity findByCode(String code);
}
