package ru.netology.maksimmosiaev.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.maksimmosiaev.OperationHistoryApiApplicationTest;
import ru.netology.maksimmosiaev.config.OperationProperties;
import ru.netology.maksimmosiaev.entity.Currency;
import ru.netology.maksimmosiaev.entity.Operation;

import static org.junit.jupiter.api.Assertions.*;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    @Autowired
    private StatementService statementService;
    @Autowired
    private OperationProperties properties;

    @Test
    public void assertThatAsyncInputOperationServiceWorksRight() throws InterruptedException {
        Operation operation = new Operation(1, 20, Currency.RUB, "Tea", 2);

        asyncInputOperationService.offerOperation(operation);
        Thread.sleep(2L * properties.getSleepMilliSeconds());

        Operation serviceOperation = statementService.getOperation(operation.getCustomerId(), 0);
        assertEquals(operation, serviceOperation);
        assertEquals(operation.getId(), serviceOperation.getId());
        assertEquals(operation.getSum(), serviceOperation.getSum());
        assertEquals(operation.getCurrency(), serviceOperation.getCurrency());
        assertEquals(operation.getMerchant(), serviceOperation.getMerchant());
    }
}