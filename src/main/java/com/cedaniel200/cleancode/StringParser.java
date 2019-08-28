package com.cedaniel200.cleancode;

import org.apache.poi.ss.usermodel.Cell;

public class StringParser implements Parser {
    @Override
    public int getCellType() {
        return Cell.CELL_TYPE_STRING;
    }

    @Override
    public String toString(Cell cell) {
        return cell.getStringCellValue();
    }
}
