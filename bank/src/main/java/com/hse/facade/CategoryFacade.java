package com.hse.facade;

import com.hse.domain.Category;
import com.hse.domain.OperationType;
import com.hse.factory.CategoryFactory;
import com.hse.utils.IdGenerator;

import java.util.*;

public class CategoryFacade {
    private final Map<String, Category> categories = new HashMap<>();

    public String createCategory(String name, OperationType type) {
        String id = IdGenerator.generate();
        Category category = CategoryFactory.createCategory(id, name, type);
        categories.put(id, category);
        return id;
    }

    public void updateCategory(String categoryId, String newName, OperationType newType) {
        Category category = getCategoryById(categoryId);
        category.setName(newName);
        category.setType(newType);
    }

    public void deleteCategory(String categoryId) {
        categories.remove(categoryId);
    }

    public Category getCategoryById(String categoryId) {
        return Optional.ofNullable(categories.get(categoryId))
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
}
