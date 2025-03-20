package com.hse.importer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.InputStream;
import java.util.List;

public class YamlImporter<T> implements DataImporter<T> {
    private final Class<T> type;
    private final ObjectMapper mapper;

    public YamlImporter(Class<T> type) {
        this.type = type;
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    @Override
    public List<T> importData(InputStream inputStream) throws Exception {
        return mapper.readValue(inputStream, 
            mapper.getTypeFactory().constructCollectionType(List.class, type));
    }
}