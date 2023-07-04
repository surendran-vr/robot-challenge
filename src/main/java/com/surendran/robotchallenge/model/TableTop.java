package com.surendran.robotchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TableTop {
    private int row;
    private int column;

    @Override
    public String toString() {
        return String.format("TableTop: %d, %d", row, column);
    }
}
