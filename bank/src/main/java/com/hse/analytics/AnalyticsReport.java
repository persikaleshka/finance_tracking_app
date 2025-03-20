package com.hse.analytics;

public class AnalyticsReport<T> {
    private final String title;
    private final T data;  // Теперь data имеет корректный параметр типа

    public AnalyticsReport(String title, T data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() { return title; }
    public T getData() { return data; }  // Исправлено: теперь getData() возвращает T

    @Override
    public String toString() {
        return title + ": " + data;
    }
}
