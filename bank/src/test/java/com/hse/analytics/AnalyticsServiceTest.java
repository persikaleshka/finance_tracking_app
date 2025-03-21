package com.hse.analytics;

import com.hse.domain.Operation;
import com.hse.domain.OperationType;
import com.hse.facade.OperationFacade;
import com.hse.facade.CategoryFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnalyticsServiceTest {
    private AnalyticsService analyticsService;
    private OperationFacade operationFacade;
    private CategoryFacade categoryFacade;

    @BeforeEach
    void setUp() {
        operationFacade = mock(OperationFacade.class);
        categoryFacade = mock(CategoryFacade.class);
        analyticsService = new AnalyticsService(operationFacade, categoryFacade);
    }

    @Test
    void testCalculateBalanceDifference() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 12, 31);

        List<Operation> operations = List.of(
                new Operation("1", OperationType.INCOME, "acc1", 500.0, LocalDate.of(2024, 3, 15), "Salary", "Job"),
                new Operation("2", OperationType.EXPENSE, "acc1", 200.0, LocalDate.of(2024, 6, 10), "Shopping", "Groceries")
        );

        when(operationFacade.getOperationsByPeriod(start, end)).thenReturn(operations);

        double balanceDiff = analyticsService.calculateBalanceDifference(start, end);
        assertEquals(300.0, balanceDiff);
    }
}
