package com.hse.facade;

import com.hse.domain.Operation;
import com.hse.domain.OperationType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsFacade {
    private final OperationFacade operationFacade;

    public AnalyticsFacade(OperationFacade operationFacade) {
        this.operationFacade = operationFacade;
    }

    public double calculateBalanceDifference(LocalDate start, LocalDate end) {
        List<Operation> operations = operationFacade.getOperationsByPeriod(start, end);
        
        double income = sumOperations(operations, OperationType.INCOME);
        double expense = sumOperations(operations, OperationType.EXPENSE);
        
        return income - expense;
    }

    public Map<String, Double> groupByCategory(OperationType type, LocalDate start, LocalDate end) {
        return operationFacade.getOperationsByPeriod(start, end).stream()
            .filter(op -> op.getType() == type)
            .collect(Collectors.groupingBy(
                Operation::getCategoryId,
                Collectors.summingDouble(Operation::getAmount)
            ));
    }

    private double sumOperations(List<Operation> operations, OperationType type) {
        return operations.stream()
            .filter(op -> op.getType() == type)
            .mapToDouble(Operation::getAmount)
            .sum();
    }
}