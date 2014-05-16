package com.punguta.services;

import com.punguta.domains.Currency;
import com.punguta.repositories.CommodityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * Created by ruslanti on 16.05.2014.
 */
@Component
public class SampleDataPopulator implements DataPopulator {

    private static final Logger logger = LoggerFactory.getLogger(SampleDataPopulator.class);

    @Autowired
    private CommodityRepository commodityRepository;

    @PostConstruct
    public void init() {
        logger.info("init");
        initCurrencies();
    }

    private void initCurrencies() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("currencies.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length == 5) {
                    Currency currency = new Currency();
                    currency.setCode(split[0]);
                    currency.setFullname(split[1]);
                    currency.setDenom(Integer.valueOf(split[2]));
                    currency.setUnit(split[3]);
                    currency.setFormat(split[4]);
                    logger.info("save "+currency);
                    commodityRepository.save(currency);
                }

            }
        } catch (IOException e) {
            logger.error("Cannot read currencies.csv", e);
        }
    }
}
