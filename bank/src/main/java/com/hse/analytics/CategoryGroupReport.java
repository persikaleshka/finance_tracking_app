package com.hse.analytics;

import java.time.LocalDate;
import java.util.Map;

public class CategoryGroupReport extends AnalyticsReport<Map<String, Double>> {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String categoryType;

    public CategoryGroupReport(Map<String, Double> data, 
                              String categoryType,
                              LocalDate start,
                              LocalDate end) {
        super("Category Group Report (" + categoryType + ")", data);
        this.categoryType = categoryType;
        this.startDate = start;
        this.endDate = end;
    }

    public String getCategoryType() { return categoryType; }
    public LocalDate getStartDate() { return startDate; }  
    public LocalDate getEndDate() { return endDate; }     

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.getTitle() + " (" + startDate + " - " + endDate + "):\n");
        for (Map.Entry<String, Double> entry : getData().entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
