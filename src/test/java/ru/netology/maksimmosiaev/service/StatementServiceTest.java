package ru.netology.maksimmosiaev.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.maksimmosiaev.OperationHistoryApiApplicationTest;
import ru.netology.maksimmosiaev.entity.Currency;
import ru.netology.maksimmosiaev.entity.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private StatementService statementService;

    @Test
    public void assertThatStatementServiceWorksRight() {
        int clientId = 1;
        int operationId = 2;
        int operationSum = 100;
        int operationClientId = 0;
        Currency operationCurrency = Currency.USD;
        String operationMerchant = "Tea";

        Operation operation = new Operation(operationId, operationSum, operationCurrency, operationMerchant, clientId);
        statementService.setOperation(clientId, operation);
        Operation serviceOperation = statementService.getOperation(clientId, operationClientId);

        assertEquals(operation, serviceOperation);
        assertEquals(operationSum, serviceOperation.getSum());
        assertEquals(operationId, serviceOperation.getId());
        assertEquals(operationMerchant, serviceOperation.getMerchant());
        assertEquals(operationCurrency, serviceOperation.getCurrency());
    }
}