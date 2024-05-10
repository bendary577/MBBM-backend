package com.mbbm.app.util.fileHandling;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVFileHandler {

    public CSVFileHandler(){}

    public void writeCSV(String filePath, String[] headers, List<String[]> records) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(headers);
            writer.writeAll(records);
        }
    }
}
