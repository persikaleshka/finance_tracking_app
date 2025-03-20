package com.hse.analytics;

import java.time.LocalDate;

public class BalanceDifferenceReport extends AnalyticsReport<Double> {  
    private final LocalDate startDate;
    private final LocalDate endDate;

    public BalanceDifferenceReport(double difference, 
                                  LocalDate start, 
                                  LocalDate end) {
        super("Balance Difference Report", difference);
        this.startDate = start;
        this.endDate = end;
    }

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}
