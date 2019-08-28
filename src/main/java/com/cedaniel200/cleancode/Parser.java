package com.cedaniel200.cleancode;

import org.apache.poi.ss.usermodel.Cell;

public interface Parser {
    int getCellType();
    String toString(Cell cell);
}
