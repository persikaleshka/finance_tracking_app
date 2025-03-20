package com.hse.domain;

public class Category {
    private final String id;
    private OperationType type;
    private String name;

    public Category(String id, OperationType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public String getId() { return id; }
    public OperationType getType() { return type; }
    public String getName() { return name; }

    public void setType(OperationType type) { this.type = type; }
    public void setName(String name) { this.name = name; }
}
