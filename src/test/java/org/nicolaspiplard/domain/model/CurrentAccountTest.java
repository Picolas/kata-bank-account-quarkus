package org.nicolaspiplard.domain.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.nicolaspiplard.domain.exception.AmountSuperiorToZeroException;
import org.nicolaspiplard.domain.exception.InsufficientFundsException;
import org.nicolaspiplard.domain.exception.OverdraftLimitExceededException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class CurrentAccountTest {

    @Test
    public void withdraw_WithSufficientBalance_ShouldDecreaseBalance() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), null);

        // When
        account.withdraw(BigDecimal.valueOf(50));

        // Then
        assertEquals(BigDecimal.valueOf(50), account.getBalance());
    }

    @Test
    public void withdraw_WithInsufficientBalance_ShouldThrowException() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), null);

        // When
        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(BigDecimal.valueOf(200));
        });

        // Then
        assertEquals("Insufficient funds: balance=100, requested=200", exception.getMessage());
    }

    @Test
    public void withdraw_WithOverdraftLimitExceeded_ShouldThrowException() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), BigDecimal.valueOf(50));

        // When
        OverdraftLimitExceededException exception = assertThrows(OverdraftLimitExceededException.class, () -> {
            account.withdraw(BigDecimal.valueOf(200));
        });

        // Then
        assertEquals("Overdraft limit exceeded: overdraft=50", exception.getMessage());
    }

    @Test
    public void deposit_WithPositiveAmount_ShouldIncreaseBalance() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), null);

        // When
        account.deposit(BigDecimal.valueOf(50));

        // Then
        assertEquals(BigDecimal.valueOf(150), account.getBalance());
    }

    @Test
    public void deposit_WithNegativeAmount_ShouldThrowException() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), null);

        // When
        AmountSuperiorToZeroException exception = assertThrows(AmountSuperiorToZeroException.class, () -> {
            account.deposit(BigDecimal.valueOf(-50));
        });

        // Then
        assertEquals("Deposit amount must be greater than zero", exception.getMessage());
    }

    @Test
    public void withdraw_WithNegativeAmount_ShouldThrowException() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), null);

        // When
        AmountSuperiorToZeroException exception = assertThrows(AmountSuperiorToZeroException.class, () -> {
            account.withdraw(BigDecimal.valueOf(-50));
        });

        // Then
        assertEquals("Withdraw amount must be greater than zero", exception.getMessage());
    }

    @Test
    public void deposit_WithZeroAmount_ShouldThrowException() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), null);

        // When
        AmountSuperiorToZeroException exception = assertThrows(AmountSuperiorToZeroException.class, () -> {
            account.deposit(BigDecimal.ZERO);
        });

        // Then
        assertEquals("Deposit amount must be greater than zero", exception.getMessage());
    }

    @Test
    public void withdraw_WithZeroAmount_ShouldThrowException() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), null);

        // When
        AmountSuperiorToZeroException exception = assertThrows(AmountSuperiorToZeroException.class, () -> {
            account.withdraw(BigDecimal.ZERO);
        });

        // Then
        assertEquals("Withdraw amount must be greater than zero", exception.getMessage());
    }

    @Test
    public void withdraw_WithSufficientBalanceAndOverdraft_ShouldDecreaseBalance() {
        // Given
        CurrentAccount account = new CurrentAccount(1L, BigDecimal.valueOf(100), BigDecimal.valueOf(50));

        // When
        account.withdraw(BigDecimal.valueOf(150));

        // Then
        assertEquals(BigDecimal.valueOf(-50), account.getBalance());
    }
}
