package org.nicolaspiplard.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.nicolaspiplard.application.port.in.GetMonthlyStatementUseCase;
import org.nicolaspiplard.application.port.in.dto.OperationDto;
import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;
import org.nicolaspiplard.application.port.out.repository.AccountRepository;
import org.nicolaspiplard.application.port.out.repository.OperationRepository;
import org.nicolaspiplard.domain.model.Account;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class GetMonthlyStatementService implements GetMonthlyStatementUseCase {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    @Inject
    public GetMonthlyStatementService(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public MonthlyStatementResponse getMonthlyStatement(Long accountId) {
        Account account = accountRepository.findByAccountId(accountId);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);

        var operations = operationRepository.findOperationsByAccountAndPeriod(accountId, oneMonthAgo, now);

        List<OperationDto> operationDtos = operations.stream()
                .map(op -> new OperationDto(
                        op.operationType().name(),
                        op.amount(),
                        op.date().toString()
                ))
                .toList();

        String accountType = account.getAccountType();

        return new MonthlyStatementResponse(
                account.getAccountId(),
                accountType,
                account.getBalance(),
                operationDtos
        );
    }
}
