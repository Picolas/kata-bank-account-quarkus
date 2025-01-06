package org.nicolaspiplard.infrastructure.adapter.out.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.nicolaspiplard.domain.model.Account;
import org.nicolaspiplard.domain.model.CurrentAccount;
import org.nicolaspiplard.domain.model.SavingAccount;
import org.nicolaspiplard.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.nicolaspiplard.infrastructure.adapter.out.persistence.entity.CurrentAccountEntity;
import org.nicolaspiplard.infrastructure.adapter.out.persistence.entity.SavingAccountEntity;

@ApplicationScoped
public class AccountMapper {
    public AccountEntity toEntity(Account account) {
        if (account instanceof CurrentAccount a) {
            CurrentAccountEntity entity = new CurrentAccountEntity();
            entity.accountId = a.getAccountId();
            entity.balance = a.getBalance();
            entity.overdraft = a.getOverdraft();

            return entity;
        } else if (account instanceof SavingAccount a) {
            SavingAccountEntity entity = new SavingAccountEntity();
            entity.accountId = a.getAccountId();
            entity.balance = a.getBalance();
            entity.depositCap = a.getDepositCap();

            return entity;
        } else {
            throw new IllegalStateException("Unknown account type: " + account.getClass());
        }
    }

    public Account toDomain(AccountEntity accountEntity) {
        if (accountEntity instanceof CurrentAccountEntity e) {
            return new CurrentAccount(
                    e.accountId,
                    e.balance,
                    e.overdraft
            );
        } else if (accountEntity instanceof SavingAccountEntity e) {
            return new SavingAccount(
                    e.accountId,
                    e.balance,
                    e.depositCap
            );
        } else {
            throw new IllegalStateException("Unknown account entity type: " + accountEntity.getClass());
        }
    }

    public void updateEntity(AccountEntity entity, Account domain) {
        entity.balance = domain.getBalance();
        if (entity instanceof SavingAccountEntity savingEntity
                && domain instanceof SavingAccount savingDomain) {
            savingEntity.depositCap = savingDomain.getDepositCap();
        }
        else if (entity instanceof CurrentAccountEntity currentEntity
                && domain instanceof CurrentAccount currentDomain) {
            currentEntity.overdraft = currentDomain.getOverdraft();
        }
        else {
            throw new IllegalArgumentException(
                    "Mismatch between entity type " + entity.getClass()
                            + " and domain type " + domain.getClass()
            );
        }
    }
}
