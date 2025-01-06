package org.nicolaspiplard.infrastructure.adapter.out.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.nicolaspiplard.domain.model.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
public class OperationEntity extends PanacheEntity {
    public Long accountId;
    public BigDecimal amount;
    public LocalDateTime date;

    @Enumerated(EnumType.STRING)
    public OperationType operationType;
}
