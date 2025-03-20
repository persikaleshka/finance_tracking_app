package com.hse.facade;

import com.hse.domain.Operation;
import com.hse.domain.OperationType;
import com.hse.factory.OperationFactory;
import com.hse.utils.IdGenerator;

import java.time.LocalDate;
import java.util.*;

public class OperationFacade {
    private final Map<String, Operation> operations = new HashMap<>();
    private final BankAccountFacade bankAccountFacade;

    public OperationFacade(BankAccountFacade bankAccountFacade) {
        this.bankAccountFacade = bankAccountFacade;
    }

    public String addOperation(OperationType type, 
                             String accountId,
                             double amount,
                             LocalDate date,
                             String description,
                             String categoryId) {
        String id = IdGenerator.generate();
        Operation operation = OperationFactory.createOperation(
            id, type, accountId, amount, date, description, categoryId
        );
        
        updateAccountBalance(accountId, type, amount);
        operations.put(id, operation);
        return id;
    }

    private void updateAccountBalance(String accountId, OperationType type, double amount) {
        double adjustment = type == OperationType.INCOME ? amount : -amount;
        bankAccountFacade.updateBalance(accountId, adjustment);
    }

    public void deleteOperation(String operationId) {
        Operation operation = getOperationById(operationId);
        reverseOperationEffect(operation);
        operations.remove(operationId);
    }

    private void reverseOperationEffect(Operation operation) {
        double adjustment = operation.getType() == OperationType.INCOME 
            ? -operation.getAmount() 
            : operation.getAmount();
        bankAccountFacade.updateBalance(operation.getAccountId(), adjustment);
    }

    public Operation getOperationById(String operationId) {
        return Optional.ofNullable(operations.get(operationId))
            .orElseThrow(() -> new IllegalArgumentException("Operation not found"));
    }

    public List<Operation> getOperationsByAccount(String accountId) {
        if (accountId == null || accountId.isEmpty()) {  
            return new ArrayList<>(operations.values());
        }
        return operations.values().stream()
            .filter(op -> op.getAccountId().equals(accountId))
            .toList();
    }

    public List<Operation> getOperationsByPeriod(LocalDate start, LocalDate end) {
        return operations.values().stream()
            .filter(op -> !op.getDate().isBefore(start) && !op.getDate().isAfter(end))
            .toList();
    }
}
