package com.mbbm.app.util.fileHandling;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

@Component
public class ExcelFileHandler {

    public byte[] generateExcelWorkbook(HashMap<Long, LinkedList<String>> data, String fileName) throws IOException {
        Workbook workbook = WorkbookFactory.create(true);
        byte[] excelFileContent = new byte[128];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            buildExcelWorkbook(workbook, data, fileName);
            excelFileContent = generateExcelFileByteArray(workbook, outputStream);
        }catch (Exception exception){
            //TODO : HANDLE THIS EXCEPTION
        }finally {
           outputStream.close();
           workbook.close();
        }
        return excelFileContent;
    }

    private void buildExcelWorkbook(Workbook workbook, HashMap<Long, LinkedList<String>> data, String fileName) throws IOException {

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
    }

    private byte[] generateExcelFileByteArray(Workbook workbook,  ByteArrayOutputStream outputStream) throws IOException {
        workbook.write(outputStream);
        return outputStream.toByteArray();
    }

    private CellStyle createHeaderRowStyle(Workbook workbook){
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return headerStyle;
    }

}
