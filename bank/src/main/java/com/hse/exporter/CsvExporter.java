package com.hse.exporter;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class CsvExporter<T> implements DataExporter<T> {
    @Override
    public void export(List<T> data, OutputStream outputStream) throws Exception {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Нет данных для экспорта.");
        }

        try (Writer writer = new OutputStreamWriter(outputStream, "UTF-8")) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                .withSeparator(',')
                .withApplyQuotesToAll(false)
                .build();
            beanToCsv.write(data);
        }
    }
}
