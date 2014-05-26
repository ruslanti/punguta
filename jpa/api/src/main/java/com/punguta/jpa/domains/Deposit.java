package com.punguta.jpa.domains;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by ruslanti on 26.05.2014.
 */
@Entity
@DiscriminatorValue("deposit")
public class Deposit extends Split {
}
