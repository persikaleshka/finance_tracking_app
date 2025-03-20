package com.hse.command;

import com.hse.facade.OperationFacade;

public class DeleteOperationCommand implements Command {
    private final OperationFacade facade;
    private final String operationId;

    public DeleteOperationCommand(OperationFacade facade, String operationId) {
        this.facade = facade;
        this.operationId = operationId;
    }

    @Override
    public void execute() {
        facade.deleteOperation(operationId);
    }
}
