package com.cedaniel200.cleancode;

import org.apache.poi.ss.usermodel.Cell;

public class NumericParser implements Parser {
    @Override
    public int getCellType() {
        return Cell.CELL_TYPE_NUMERIC;
    }

    @Override
    public String toString(Cell cell) {
        return String.valueOf(cell.getNumericCellValue()).replace(".0", "");
    }
}
