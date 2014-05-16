package com.punguta.domains;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by ruslanti on 16.05.2014.
 */
@Entity
@DiscriminatorValue("CURRENCY")
public class Currency extends Commodity{
}
