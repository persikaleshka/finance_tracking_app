package com.hse.command;

import com.hse.facade.BankAccountFacade;

public class DeleteAccountCommand implements Command {
    private final BankAccountFacade facade;
    private final String accountId;

    public DeleteAccountCommand(BankAccountFacade facade, String accountId) {
        this.facade = facade;
        this.accountId = accountId;
    }

    @Override
    public void execute() {
        facade.deleteAccount(accountId);
    }
}