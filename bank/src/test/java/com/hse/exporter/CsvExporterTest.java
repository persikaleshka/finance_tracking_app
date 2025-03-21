package com.hse.exporter;

import com.hse.domain.BankAccount;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CsvExporterTest {
    @Test
    void testExport() throws Exception {
        CsvExporter<BankAccount> exporter = new CsvExporter<>();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        BankAccount acc = new BankAccount("1", "Savings", 1000.0);
        exporter.export(List.of(acc), outputStream);

        String result = outputStream.toString("UTF-8");
        assertTrue(result.contains("Savings"));
    }
}
