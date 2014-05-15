package com.punguta;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * User: ruslan
 * Date: 5/14/14
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    @Bean
    DataSource dataSource() {
        return new SimpleDriverDataSource() {{
            setDriverClass(org.h2.Driver.class);
            setUsername("sa");
            setUrl("jdbc:h2:mem");
            setPassword("");
        }};
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
