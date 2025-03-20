package com.hse.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.OutputStream;
import java.util.List;

public class YamlExporter<T> implements DataExporter<T> {
    private final ObjectMapper mapper;

    public YamlExporter() {
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    @Override
    public void export(List<T> data, OutputStream outputStream) throws Exception {
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputStream, data);
    }
}
