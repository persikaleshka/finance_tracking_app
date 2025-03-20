package com.hse.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.util.List;

public class JsonExporter<T> implements DataExporter<T> {
    private final ObjectMapper mapper;

    public JsonExporter() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public void export(List<T> data, OutputStream outputStream) throws Exception {
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputStream, data);
    }
}
