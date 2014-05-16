package com.punguta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by ruslanti on 16.05.2014.
 */
@Component
public class SampleDataPopulator implements DataPopulator {

    private static final Logger logger = LoggerFactory.getLogger(SampleDataPopulator.class);

    @PostConstruct
    public void init() {
        logger.info("init");
    }
}
