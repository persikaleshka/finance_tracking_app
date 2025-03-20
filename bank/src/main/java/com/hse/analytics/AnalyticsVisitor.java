package com.hse.analytics;

import com.hse.domain.Operation;

public interface AnalyticsVisitor<T> {  // Добавлен обобщенный параметр T
    void visit(Operation operation);
    AnalyticsReport<T> getReport();  // Теперь метод возвращает AnalyticsReport<T>
}
