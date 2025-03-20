package com.hse.command;

import com.hse.facade.BankAccountFacade;

public class CreateAccountCommand implements Command {
    private final BankAccountFacade facade;
    private final String name;
    private final double balance;

    public CreateAccountCommand(BankAccountFacade facade, String name, double balance) {
        this.facade = facade;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public void execute() {
        facade.createAccount(name, balance);
        System.out.println("Счет '" + name + "' создан!");
    }
}
