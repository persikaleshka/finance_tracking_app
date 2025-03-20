package com.hse.command;

import com.hse.domain.OperationType;
import com.hse.facade.CategoryFacade;

public class CreateCategoryCommand implements Command {
    private final CategoryFacade facade;
    private final String name;
    private final OperationType type;

    public CreateCategoryCommand(CategoryFacade facade, String name, OperationType type) {
        this.facade = facade;
        this.name = name;
        this.type = type;
    }

    @Override
    public void execute() {
        facade.createCategory(name, type);
        System.out.println("Категория '" + name + "' создана!");
    }
}
