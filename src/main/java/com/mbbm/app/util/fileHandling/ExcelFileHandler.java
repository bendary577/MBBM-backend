package com.mbbm.app.util.fileHandling;

import org.apache.poi.ss.usermodel.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class ExcelFileHandler {

    public Workbook generateExcelWorkbook(HashMap<Long, LinkedList<String>> data, String fileName) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(true)) {
            Sheet sheet = workbook.createSheet(fileName);

            CellStyle headerStyle = createHeaderRowStyle(workbook);
            CellStyle generalStyle = workbook.createCellStyle();

            int rowCount = 0;
            for (Long recordId : data.keySet()) {
                Row row = sheet.createRow(rowCount);
                LinkedList<String> dataValues = data.get(recordId);
                int cellCount = 0;
                for (String value : dataValues) {
                    Cell cell = row.createCell(cellCount);
                    cell.setCellValue(value);
                    if (rowCount == 0) {
                        cell.setCellStyle(headerStyle);
                    } else {
                        cell.setCellStyle(generalStyle);
                    }
                    cellCount++;
                }
                rowCount++;
            }
            return workbook;
        }
    }

    public CellStyle createHeaderRowStyle(Workbook workbook){
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return headerStyle;
    }

    public byte[] generateExcelFileByteArray(Workbook workbook) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream.toByteArray();
    }
}
