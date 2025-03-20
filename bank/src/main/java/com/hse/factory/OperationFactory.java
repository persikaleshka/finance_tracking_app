// OperationFactory.java
package com.hse.factory;

import com.hse.domain.Operation;
import com.hse.domain.OperationType;
import com.hse.exceptions.InvalidDataException;

import java.time.LocalDate;

public class OperationFactory {
    public static Operation createOperation(
        String id,
        OperationType type,
        String accountId,
        double amount,
        LocalDate date,
        String description,
        String categoryId
    ) {
        validateOperationData(type, amount, date, accountId, categoryId);
        
        return new Operation(
            id,
            type,
            accountId,
            amount,
            date,
            description,
            categoryId
        );
    }

    private static void validateOperationData(
        OperationType type,
        double amount,
        LocalDate date,
        String accountId,
        String categoryId
    ) {
        if (type == null) {
            throw new InvalidDataException("Тип операции должен быть указан");
        }
        if (amount <= 0) {
            throw new InvalidDataException("Сумма операции должна быть положительной");
        }
        if (date == null) {
            throw new InvalidDataException("Дата операции должна быть указана");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new InvalidDataException("Дата операции не может быть в будущем");
        }
        if (accountId == null || accountId.isBlank()) {
            throw new InvalidDataException("Не указан счет операции");
        }
        if (categoryId == null || categoryId.isBlank()) {
            throw new InvalidDataException("Не указана категория операции");
        }
    }
}