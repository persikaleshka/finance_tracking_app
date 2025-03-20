package com.hse.factory;

import com.hse.domain.Category;
import com.hse.domain.OperationType;
import com.hse.exceptions.InvalidDataException;

public class CategoryFactory {
    public static Category createCategory(String id, String name, OperationType type) {
        if (name == null || name.isBlank()) {
            throw new InvalidDataException("Название категории не может быть пустым");
        }
        if (type == null) {
            throw new InvalidDataException("Тип категории должен быть указан");
        }
        return new Category(id, type, name);
    }
}