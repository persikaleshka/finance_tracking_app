package com.hse.importer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class JsonImporter<T> implements DataImporter<T> {
    private final Class<T> type;
    private final ObjectMapper mapper;

    public JsonImporter(Class<T> type) {
        this.type = type;
        this.mapper = new ObjectMapper();
    }

    @Override
    public List<T> importData(InputStream inputStream) throws Exception {
        return mapper.readValue(inputStream, 
            mapper.getTypeFactory().constructCollectionType(List.class, type));
    }
}
