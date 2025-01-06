package org.nicolaspiplard.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("SAVING")
public class SavingAccountEntity extends AccountEntity {
    public BigDecimal depositCap;
}
