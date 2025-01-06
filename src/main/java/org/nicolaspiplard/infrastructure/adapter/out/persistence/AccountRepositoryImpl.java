package org.nicolaspiplard.infrastructure.adapter.out.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.nicolaspiplard.domain.exception.AccountNotFoundException;
import org.nicolaspiplard.domain.model.Account;
import org.nicolaspiplard.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.nicolaspiplard.infrastructure.adapter.out.persistence.mapper.AccountMapper;
import org.nicolaspiplard.application.port.out.repository.AccountRepository;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {
    @Inject
    AccountMapper accountMapper;

    @Override
    public Account findByAccountId(Long accountId) {
        AccountEntity entity = AccountEntity.find("accountId", accountId).firstResult();
        if (entity == null) {
            throw new AccountNotFoundException(accountId);
        }
        return accountMapper.toDomain(entity);
    }

    @Override
    public void save(Account account) {
        AccountEntity existingEntity = AccountEntity.find("accountId", account.getAccountId()).firstResult();
        if (existingEntity == null) {
            AccountEntity newEntity = accountMapper.toEntity(account);
            newEntity.persist();
        } else {
            accountMapper.updateEntity(existingEntity, account);
            existingEntity.persist();
        }
    }
}
