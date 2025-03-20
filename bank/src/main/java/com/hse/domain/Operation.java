package com.hse.domain;

import java.time.LocalDate;

public class Operation {
    private final String id;
    private final OperationType type;
    private final String accountId;
    private final double amount;
    private final LocalDate date;
    private final String description;
    private final String categoryId;

    public Operation(String id, OperationType type, String accountId, 
                    double amount, LocalDate date, String description, 
                    String categoryId) {
        this.id = id;
        this.type = type;
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() { return id; }
    public OperationType getType() { return type; }
    public String getAccountId() { return accountId; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public String getCategoryId() { return categoryId; }
}
