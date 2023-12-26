package ru.netology.maksimmosiaev.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.maksimmosiaev.config.OperationProperties;
import ru.netology.maksimmosiaev.entity.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Service
@RequiredArgsConstructor
public class AsyncInputOperationService {
    private final Queue<Operation> queue = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProperties properties;

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    @PostConstruct
    private void postInit() {
        this.startAsyncOperationProcessing();
    }

    private void startAsyncOperationProcessing() {
        new Thread(this::processQueue).start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();

            if (operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(properties.getSleepMilliSeconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation:" + operation);

                statementService.setOperation(operation.getCustomerId(), operation);
            }
        }
    }
}
