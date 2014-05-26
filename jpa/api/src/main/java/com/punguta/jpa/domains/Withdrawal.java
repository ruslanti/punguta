package com.punguta.jpa.domains;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by ruslanti on 26.05.2014.
 */
@Entity
@DiscriminatorValue("withdrawal")
public class Withdrawal extends Split{
}
