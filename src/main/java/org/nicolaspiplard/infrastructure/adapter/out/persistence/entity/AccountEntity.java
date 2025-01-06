package org.nicolaspiplard.infrastructure.adapter.out.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
@Table(name = "account")
public class AccountEntity extends PanacheEntity {

    @Column(unique = true)
    public Long accountId;

    public BigDecimal balance;
}
