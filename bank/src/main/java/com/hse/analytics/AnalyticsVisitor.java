package com.hse.analytics;

import com.hse.domain.Operation;

public interface AnalyticsVisitor<T> {  
    void visit(Operation operation);
    AnalyticsReport<T> getReport();  
}
