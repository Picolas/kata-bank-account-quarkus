package org.nicolaspiplard.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CURRENT")
public class CurrentAccountEntity extends AccountEntity {
    public BigDecimal overdraft;
}
