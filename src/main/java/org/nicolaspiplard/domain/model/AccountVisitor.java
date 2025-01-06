package org.nicolaspiplard.domain.model;

public interface AccountVisitor<R> {
    R visit(CurrentAccount current);
    R visit(SavingAccount saving);
}
