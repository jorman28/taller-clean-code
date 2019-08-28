package com.cedaniel200.cleancode;

import org.apache.poi.ss.usermodel.Cell;

import java.util.HashMap;
import java.util.Optional;

public class ParserSelecter {

    private HashMap<Integer, Parser> parsers;

    public ParserSelecter() {
        this.parsers = new  HashMap<Integer, Parser>();
    }

    public void register(Parser parser){
        parsers.put(parser.getCellType(), parser);
    }

    Optional<Parser> selectByTypeCell(Cell cell){
        return Optional.ofNullable(this.parsers.get(cell.getCellType()));
    }
}
