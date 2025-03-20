package com.hse.exporter;

public class ExporterFactory {
    public static <T> DataExporter<T> createExporter(String format) {
        return switch (format.toLowerCase()) {
            case "json" -> new JsonExporter<>();
            case "csv" -> new CsvExporter<>();
            case "yaml" -> new YamlExporter<>();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}   