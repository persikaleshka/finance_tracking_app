package com.hse.factory;

import com.hse.domain.BankAccount;
import com.hse.exceptions.InvalidDataException;

public class BankAccountFactory {
    public static BankAccount createAccount(String id, String name, double balance) {
        if (balance < 0) {
            throw new InvalidDataException("Баланс счета не может быть отрицательным");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidDataException("Название счета не может быть пустым");
        }
        return new BankAccount(id, name, balance);
    }
}