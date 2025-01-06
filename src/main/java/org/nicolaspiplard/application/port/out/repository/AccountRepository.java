package org.nicolaspiplard.application.port.out.repository;

import org.nicolaspiplard.domain.model.Account;

public interface AccountRepository {
    Account findByAccountId(Long accountId);
    void save(Account account);
}
