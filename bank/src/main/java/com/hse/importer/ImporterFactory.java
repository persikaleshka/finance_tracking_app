package com.hse.importer;

public class ImporterFactory {
    public static <T> DataImporter<T> createImporter(String format, Class<T> type) {
        return switch (format.toLowerCase()) {
            case "json" -> new JsonImporter<>(type);
            case "csv" -> new CsvImporter<>(type);
            case "yaml" -> new YamlImporter<>(type);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}