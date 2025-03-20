package com.hse.importer;

import java.io.InputStream;
import java.util.List;

public interface DataImporter<T> {
    List<T> importData(InputStream inputStream) throws Exception;
}