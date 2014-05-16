package com.punguta.domains;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
@DiscriminatorValue("income")
public class Income extends Account{
}
