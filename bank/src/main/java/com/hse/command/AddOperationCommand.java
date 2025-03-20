package com.hse.command;

import com.hse.domain.OperationType;
import com.hse.facade.OperationFacade;
import java.time.LocalDate;

public class AddOperationCommand implements Command {
    private final OperationFacade facade;
    private final OperationType type;
    private final String accountId;
    private final double amount;
    private final LocalDate date;
    private final String description;
    private final String categoryId;
    private String operationId;

    public AddOperationCommand(OperationFacade facade, 
                              OperationType type,
                              String accountId,
                              double amount,
                              LocalDate date,
                              String description,
                              String categoryId) {
        this.facade = facade;
        this.type = type;
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }

    @Override
    public void execute() {
        operationId = facade.addOperation(
            type, accountId, amount, date, description, categoryId
        );
    }

    public String getOperationId() {
        return operationId;
    }
}