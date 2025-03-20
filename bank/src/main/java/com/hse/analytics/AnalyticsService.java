package com.hse.analytics;

import com.hse.domain.Operation;
import com.hse.domain.OperationType;
import com.hse.facade.OperationFacade;
import com.hse.facade.CategoryFacade;
import java.time.LocalDate;
import java.util.List;

public class AnalyticsService {
    private final OperationFacade operationFacade;
    private final CategoryFacade categoryFacade;  

    public AnalyticsService(OperationFacade operationFacade, CategoryFacade categoryFacade) {
        this.operationFacade = operationFacade;
        this.categoryFacade = categoryFacade;
    }

    public BalanceDifferenceReport getBalanceDifference(LocalDate start, LocalDate end) {
        BalanceAnalyticsVisitor visitor = new BalanceAnalyticsVisitor(start, end);
        processOperations(visitor, start, end);
        return (BalanceDifferenceReport) visitor.getReport();
    }

    public double calculateBalanceDifference(LocalDate start, LocalDate end) {
        BalanceDifferenceReport report = getBalanceDifference(start, end);
        return (double) report.getData();  
    }

    public CategoryGroupReport getCategoryReport(OperationType type, 
                                                LocalDate start, 
                                                LocalDate end) {
        CategoryAnalyticsVisitor visitor = new CategoryAnalyticsVisitor(type, start, end, categoryFacade);
        processOperations(visitor, start, end);
        return (CategoryGroupReport) visitor.getReport();
    }

    private <T> void processOperations(AnalyticsVisitor<T> visitor, 
                                       LocalDate start, 
                                       LocalDate end) {
        List<Operation> operations = operationFacade.getOperationsByPeriod(start, end);
        operations.forEach(visitor::visit);
    }
}
