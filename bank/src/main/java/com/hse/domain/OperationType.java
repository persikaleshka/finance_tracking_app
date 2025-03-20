package com.hse.domain;

public enum OperationType {
    INCOME("Доход"),
    EXPENSE("Расход");

    private final String displayName;

    OperationType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
