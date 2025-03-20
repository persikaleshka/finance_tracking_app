package com.hse.exporter;

import java.io.OutputStream;
import java.util.List;

public interface DataExporter<T> {
    void export(List<T> data, OutputStream outputStream) throws Exception;
}