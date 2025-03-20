package com.hse.importer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CsvImporter<T> implements DataImporter<T> {
    private final Class<T> type;

    public CsvImporter(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> importData(InputStream inputStream) throws Exception {
        try (Reader reader = new InputStreamReader(inputStream, "UTF-8")) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                .withType(type)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .build();
            return csvToBean.parse();
        }
    }
}
