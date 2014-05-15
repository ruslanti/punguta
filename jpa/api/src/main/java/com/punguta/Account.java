package com.punguta;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String code;

    private String description;

    private Commodity commodity;

    private Book book;

    private  boolean hidden;

    private AccountType type;

    private Date createdAt;

    private Date updatedAt;

}
