package org.nicolaspiplard.infrastructure.adapter.in.rest;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nicolaspiplard.application.port.out.repository.AccountRepository;
import org.nicolaspiplard.domain.model.CurrentAccount;
import org.nicolaspiplard.domain.model.SavingAccount;
import org.nicolaspiplard.infrastructure.adapter.in.rest.dto.request.DepositRequest;
import org.nicolaspiplard.infrastructure.adapter.in.rest.dto.request.WithdrawRequest;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class AccountResourceTest {

    @Inject
    AccountRepository accountRepository;

    @BeforeEach
    @Transactional
    public void setUp() {

        CurrentAccount currentAccount = new CurrentAccount(
                1L,
                BigDecimal.valueOf(100),
                BigDecimal.ZERO
        );
        accountRepository.save(currentAccount);

        SavingAccount savingAccount = new SavingAccount(
                2L,
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(1000)
        );

        accountRepository.save(savingAccount);
    }


    @Test
    public void deposit_WithPositiveAmount_ShouldIncreaseBalance() {
        DepositRequest depositRequest = new DepositRequest(BigDecimal.valueOf(50));

        given()
                .pathParam("accountId", 1)
                .contentType(ContentType.JSON)
                .body(depositRequest)
                .when()
                .post("/accounts/{accountId}/deposit")
                .then()
                .statusCode(200)
                .body("balance", is(150.0F));
    }

    @Test
    public void deposit_WithNegativeAmount_ShouldReturnBadRequest() {
        DepositRequest depositRequest = new DepositRequest(BigDecimal.valueOf(-50));

        given()
                .pathParam("accountId", 1)
                .contentType(ContentType.JSON)
                .body(depositRequest)
                .when()
                .post("/accounts/{accountId}/deposit")
                .then()
                .statusCode(400);
    }

    @Test
    public void withdraw_WithPositiveAmount_ShouldDecreaseBalance() {
        WithdrawRequest withdrawRequest = new WithdrawRequest(BigDecimal.valueOf(50));

        given()
                .pathParam("accountId", 1)
                .contentType(ContentType.JSON)
                .body(withdrawRequest)
                .when()
                .post("/accounts/{accountId}/withdraw")
                .then()
                .statusCode(200)
                .body("balance", is(50.0F));
    }

    @Test
    public void withdraw_WithExceededAmount_ShouldReturnBadRequest() {
        WithdrawRequest withdrawRequest = new WithdrawRequest(BigDecimal.valueOf(150));

        given()
                .pathParam("accountId", 1)
                .contentType(ContentType.JSON)
                .body(withdrawRequest)
                .when()
                .post("/accounts/{accountId}/withdraw")
                .then()
                .statusCode(400);
    }

    // Monthly statement tests
    @Test
    public void getMonthlyStatement_WithCurrentAccount_ShouldReturnMonthlyStatement() {
        given()
                .pathParam("accountId", 1)
                .when()
                .get("/accounts/{accountId}/monthly-statement")
                .then()
                .statusCode(200)
                .body("accountId", is(1))
                .body("balance", is(100.0F));
    }

    @Test
    public void getMonthlyStatement_WithSavingAccount_ShouldReturnMonthlyStatement() {
        given()
                .pathParam("accountId", 2)
                .when()
                .get("/accounts/{accountId}/monthly-statement")
                .then()
                .log().all()
                .statusCode(200)
                .body("accountId", is(2))
                .body("balance", is(1000.0F));
    }

    @Test
    public void getMonthlyStatement_WithNonExistingAccount_ShouldReturnNotFound() {
        given()
                .pathParam("accountId", 10)
                .when()
                .get("/accounts/{accountId}/monthly-statement")
                .then()
                .statusCode(404);
    }
}
