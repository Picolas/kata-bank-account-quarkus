package org.nicolaspiplard.infrastructure.adapter.out.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.nicolaspiplard.application.port.out.repository.OperationRepository;
import org.nicolaspiplard.domain.model.Operation;
import org.nicolaspiplard.infrastructure.adapter.out.persistence.entity.OperationEntity;
import org.nicolaspiplard.infrastructure.adapter.out.persistence.mapper.OperationMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OperationRepositoryImpl implements OperationRepository {

    @Inject
    OperationMapper operationMapper;

    @Override
    public void recordOperation(Operation operation) {
        OperationEntity entity = operationMapper.toEntity(operation);
        entity.persist();
    }

    @Override
    public List<Operation> findOperationsByAccountAndPeriod(Long accountId, LocalDateTime from, LocalDateTime to) {
        List<OperationEntity> entities = OperationEntity.find(
                "accountId = ?1 and date >= ?2 and date <= ?3 order by date desc",
                accountId, from, to
        ).list();

        return entities.stream()
                .map(operationMapper::toDomain)
                .collect(Collectors.toList());
    }
}
