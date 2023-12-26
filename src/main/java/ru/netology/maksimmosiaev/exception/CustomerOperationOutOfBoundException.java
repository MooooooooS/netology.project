package ru.netology.maksimmosiaev.exception;

public class CustomerOperationOutOfBoundException extends OperationRuntimeException {
    public static final String EXCEPTION_MESSAGE = "Exception while trying to save operation %s for customer %s";
    private final int customerId;
    private final int operationId;

    public CustomerOperationOutOfBoundException(int customerId, int operationId) {
        super();
        this.customerId = customerId;
        this.operationId = operationId;
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE.formatted(operationId, customerId);
    }
}
