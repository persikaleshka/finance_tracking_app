package com.hse.facade;

import com.hse.domain.BankAccount;
import com.hse.factory.BankAccountFactory;
import com.hse.utils.IdGenerator;

import java.util.*;

public class BankAccountFacade {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public String createAccount(String name, double initialBalance) {
        String id = IdGenerator.generate();
        BankAccount account = BankAccountFactory.createAccount(id, name, initialBalance);
        accounts.put(id, account);
        return id;
    }

    public void updateAccountName(String accountId, String newName) {
        BankAccount account = getAccountById(accountId);
        account.setName(newName);
    }

    public void deleteAccount(String accountId) {
        accounts.remove(accountId);
    }

    public BankAccount getAccountById(String accountId) {
        return Optional.ofNullable(accounts.get(accountId))
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public void updateBalance(String accountId, double amount) {
        BankAccount account = getAccountById(accountId);
        account.setBalance(account.getBalance() + amount);
    }

    // public void recalculateBalance(String accountId) {
    // double correctBalance = operationFacade.getOperationsByAccount(accountId)
    //     .stream()
    //     .mapToDouble(op -> op.getType() == OperationType.INCOME ? op.getAmount() : -op.getAmount())
    //     .sum();
    // BankAccount account = getAccountById(accountId);
    // account.setBalance(correctBalance);
//}
}
