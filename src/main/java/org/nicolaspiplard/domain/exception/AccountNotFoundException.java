package org.nicolaspiplard.domain.exception;

public class AccountNotFoundException extends RuntimeException {

    private final Long accountId;

    public AccountNotFoundException(Long accountId) {
        super("Account with id " + accountId + " not found");
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }
}
