package com.hse.command;

import com.hse.facade.BankAccountFacade;

public class UpdateAccountCommand implements Command {
    private final BankAccountFacade facade;
    private final String accountId;
    private final String newName;

    public UpdateAccountCommand(BankAccountFacade facade, 
                               String accountId, 
                               String newName) {
        this.facade = facade;
        this.accountId = accountId;
        this.newName = newName;
    }

    @Override
    public void execute() {
        facade.updateAccountName(accountId, newName);
    }
}