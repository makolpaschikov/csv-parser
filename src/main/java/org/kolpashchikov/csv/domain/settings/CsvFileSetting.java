package org.kolpashchikov.csv.domain.settings;

import static org.kolpashchikov.csv.util.Constants.COMMA;
import static org.kolpashchikov.csv.util.Constants.QUOTES;


public record CsvFileSetting(
        Boolean firsLineIsColumnNames,
        String columnSeparator,
        String stringSeparator
) {

    public CsvFileSetting() {
        this(true, COMMA, QUOTES);
    }

}
