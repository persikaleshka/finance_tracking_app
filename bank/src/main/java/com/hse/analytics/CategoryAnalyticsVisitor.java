package com.hse.analytics;

import com.hse.domain.Operation;
import com.hse.domain.OperationType;
import com.hse.facade.CategoryFacade;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CategoryAnalyticsVisitor implements AnalyticsVisitor<Map<String, Double>> {
    private final Map<String, Double> categoryStats = new HashMap<>();
    private final OperationType targetType;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final CategoryFacade categoryFacade;

    public CategoryAnalyticsVisitor(OperationType type, 
                                   LocalDate start, 
                                   LocalDate end, 
                                   CategoryFacade categoryFacade) {
        this.targetType = type;
        this.startDate = start;
        this.endDate = end;
        this.categoryFacade = categoryFacade;
    }

    @Override
    public void visit(Operation operation) {
        if (operation.getType() == targetType && isInPeriod(operation.getDate())) {
            String categoryId = operation.getCategoryId();
            String categoryName = categoryFacade.getCategoryById(categoryId).getName(); 
            categoryStats.merge(categoryName, operation.getAmount(), Double::sum);
        }
    }

    @Override
    public AnalyticsReport<Map<String, Double>> getReport() {
        return new CategoryGroupReport(
            new HashMap<>(categoryStats), 
            targetType.toString(),
            startDate,
            endDate
        );
    }

    private boolean isInPeriod(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
