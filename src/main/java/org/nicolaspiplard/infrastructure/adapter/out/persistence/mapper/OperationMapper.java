package org.nicolaspiplard.infrastructure.adapter.out.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.nicolaspiplard.domain.model.Operation;
import org.nicolaspiplard.infrastructure.adapter.out.persistence.entity.OperationEntity;

@ApplicationScoped
public class OperationMapper {

    public OperationEntity toEntity(Operation operation) {
        OperationEntity entity = new OperationEntity();
        entity.accountId = operation.accountId();
        entity.amount = operation.amount();
        entity.date = operation.date();
        entity.operationType = operation.operationType();
        return entity;
    }

    public Operation toDomain(OperationEntity entity) {
        return new Operation(
                entity.id,
                entity.accountId,
                entity.amount,
                entity.date,
                entity.operationType
        );
    }
}
