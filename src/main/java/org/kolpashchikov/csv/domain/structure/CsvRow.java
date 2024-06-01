package org.kolpashchikov.csv.domain.structure;

import java.util.List;

public class CsvRow {

    private final List<CsvColumn> csvColumns;

    public CsvRow(List<CsvColumn> csvColumns) {
        this.csvColumns = csvColumns;
    }

    public CsvColumn get(int index) {
        return csvColumns.get(index);
    }

    public int size() {
        return csvColumns.size();
    }

}
