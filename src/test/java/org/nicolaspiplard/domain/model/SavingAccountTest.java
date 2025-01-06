package org.nicolaspiplard.domain.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.nicolaspiplard.domain.exception.DepositCapExceededException;
import org.nicolaspiplard.domain.exception.InsufficientFundsSavingAccountException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class SavingAccountTest {

    @Test
    public void deposit_WithAmountExceedingDepositCap_ShouldThrowException() {
        // Given
        SavingAccount account = new SavingAccount(1L, BigDecimal.valueOf(100));

        // When
        DepositCapExceededException exception = assertThrows(DepositCapExceededException.class, () -> {
            account.deposit(BigDecimal.valueOf(200));
        });

        // Then
        assertEquals("Deposit cap exceeded: balance=0, requested=200, depositCap=100", exception.getMessage());
    }

    @Test
    public void withdraw_WithInsufficientBalance_ShouldThrowException() {
        // Given
        SavingAccount account = new SavingAccount(1L, BigDecimal.valueOf(100));

        // When
        InsufficientFundsSavingAccountException exception = assertThrows(InsufficientFundsSavingAccountException.class, () -> {
            account.withdraw(BigDecimal.valueOf(200));
        });

        // Then
        assertEquals("Insufficient funds on saving account: balance=0, requested=200", exception.getMessage());
    }

    @Test
    public void withdraw_WithSufficientBalance_ShouldDecreaseBalance() {
        // Given
        SavingAccount account = new SavingAccount(1L, BigDecimal.valueOf(100));
        account.deposit(BigDecimal.valueOf(100));

        // When
        account.withdraw(BigDecimal.valueOf(50));

        // Then
        assertEquals(BigDecimal.valueOf(50), account.getBalance());
    }

    @Test
    public void deposit_WithPositiveAmount_ShouldIncreaseBalance() {
        // Given
        SavingAccount account = new SavingAccount(1L, BigDecimal.valueOf(100));

        // When
        account.deposit(BigDecimal.valueOf(50));

        // Then
        assertEquals(BigDecimal.valueOf(50), account.getBalance());
    }
}
