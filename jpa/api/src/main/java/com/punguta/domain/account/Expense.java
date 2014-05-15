package com.punguta.domain.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
@DiscriminatorValue("expense")
public class Expense extends Account{
}
