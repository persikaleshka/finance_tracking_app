package com.hse.command;

import com.hse.facade.CategoryFacade;

public class DeleteCategoryCommand implements Command {
    private final CategoryFacade facade;
    private final String categoryId;

    public DeleteCategoryCommand(CategoryFacade facade, String categoryId) {
        this.facade = facade;
        this.categoryId = categoryId;
    }

    @Override
    public void execute() {
        facade.deleteCategory(categoryId);
    }
}