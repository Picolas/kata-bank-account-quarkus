package org.nicolaspiplard.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.nicolaspiplard.application.port.in.WithdrawUseCase;
import org.nicolaspiplard.application.port.in.response.WithdrawResponse;
import org.nicolaspiplard.application.port.out.repository.OperationRepository;
import org.nicolaspiplard.domain.model.Account;
import org.nicolaspiplard.application.port.out.repository.AccountRepository;
import org.nicolaspiplard.domain.model.Operation;
import org.nicolaspiplard.domain.model.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class WithdrawService implements WithdrawUseCase {

    private final AccountRepository accountRepository;

    private final OperationRepository operationRepository;

    @Inject
    public WithdrawService(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    @Transactional
    public WithdrawResponse withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findByAccountId(accountId);
        account.withdraw(amount);
        accountRepository.save(account);

        OperationType operationType = OperationType.WITHDRAW;
        Operation operation = new Operation(null, accountId, amount,  LocalDateTime.now(), operationType);
        operationRepository.recordOperation(operation);

        return new WithdrawResponse(account.getBalance());
    }
}
