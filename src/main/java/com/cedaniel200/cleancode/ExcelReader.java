package com.cedaniel200.cleancode;

import jline.internal.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelReader {

    private static final String PATH_OF_TEST_RESOURCE = System.getProperty("user.dir") + "//src//test//resources//";
    private String fileName;

    private ExcelReader(String fileName) {
        this.fileName = fileName;
    }

    public static ExcelReader createExcelReader(String fileName) {
        return new ExcelReader(fileName);
    }

    public List<HashMap<String, String>> getRowsBySheetName(String sheetName) throws ExcelException {
        try (FileInputStream excelFile = new FileInputStream(PATH_OF_TEST_RESOURCE + fileName)) {
            return tryGetRowsBySheetName(sheetName, excelFile);
        } catch (Exception e) {
            throw new ExcelException(String.format("Error al leer el archivo con nombre %s", fileName), e);
        }
    }

    private List<HashMap<String, String>> tryGetRowsBySheetName(String sheetName, FileInputStream excelFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row headerRow = sheet.getRow(0);
        return getRowsBySheet(sheet, headerRow);
    }

    public List<HashMap<String, String>> getRowsBySheet(XSSFSheet sheet, Row headerRow) {
        List<HashMap<String, String>> rows = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row currentRow = sheet.getRow(i);
            rows.add(getCellsByRow(currentRow, headerRow));
        }
        return rows;
    }

    private HashMap<String, String> getCellsByRow(Row row, Row headerRow) {
        HashMap<String, String> cells = new HashMap<String, String>();
        for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
            Cell currentCell = row.getCell(j);
            String key = headerRow.getCell(j).getStringCellValue();
            String value = "";
            switch (currentCell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = currentCell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = String.valueOf(currentCell.getNumericCellValue()).replace(".0", "");
                    break;
                // faltan mas tipo por implementar
                default:
                    break;
            }
            cells.put(key, value);
        }
        return cells;
    }
}
