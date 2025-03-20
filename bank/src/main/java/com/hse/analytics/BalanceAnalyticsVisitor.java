package com.hse.analytics;

import com.hse.domain.Operation;
import com.hse.domain.OperationType;
import java.time.LocalDate;

public class BalanceAnalyticsVisitor implements AnalyticsVisitor<Double> {  // Указываем Double
    private double totalIncome = 0;
    private double totalExpense = 0;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public BalanceAnalyticsVisitor(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public void visit(Operation operation) {
        if (isInPeriod(operation.getDate())) {
            if (operation.getType() == OperationType.INCOME) {
                totalIncome += operation.getAmount();
            } else {
                totalExpense += operation.getAmount();
            }
        }
    }

    @Override
    public AnalyticsReport<Double> getReport() {  // Исправлено: добавлен параметр Double
        return new BalanceDifferenceReport(
            totalIncome - totalExpense, 
            startDate, 
            endDate
        );
    }

    private boolean isInPeriod(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
