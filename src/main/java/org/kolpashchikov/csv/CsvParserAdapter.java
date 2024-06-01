package org.kolpashchikov.csv;

import org.kolpashchikov.csv.domain.settings.CsvFileSetting;
import org.kolpashchikov.csv.domain.structure.CsvColumn;
import org.kolpashchikov.csv.domain.structure.CsvRow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.kolpashchikov.csv.util.Constants.*;

public class CsvParserAdapter implements CsvParser {

    private final CsvRow headers;

    private final CsvFileSetting csvFileSetting;

    private final BufferedReader reader;


    public CsvParserAdapter(String csvFilePath, CsvFileSetting csvFileSetting) throws IOException {
        this.csvFileSetting = csvFileSetting;
        this.reader = new BufferedReader(new FileReader(csvFilePath));

        CsvRow firstRow = next();
        if (csvFileSetting.firsLineIsColumnNames()) {
            headers = firstRow;
        } else {
            List<CsvColumn> csvColumns = new ArrayList<>(firstRow.size());
            for (int i = 1; i <= firstRow.size(); i++) {
                csvColumns.add(new CsvColumn(ATTRIBUTE_NAME_PREFIX.formatted(i)));
            }

            headers = new CsvRow(csvColumns);
            reader.reset();
        }
    }

    public CsvParserAdapter(String csvFilePath) throws IOException {
        this(csvFilePath, new CsvFileSetting());
    }

    @Override
    public CsvRow next() throws IOException {
        String row = reader.readLine();
        if (isNull(row)) {
            return null;
        }

        String[] splitRow = splitRow(row);

        List<CsvColumn> csvColumns = new ArrayList<>(splitRow.length);
        for (String column : splitRow) {
            csvColumns.add(new CsvColumn(column));
        }

        return new CsvRow(csvColumns);
    }

    private String[] splitRow(String row) {
        String columnSeparator = csvFileSetting.columnSeparator();
        String stringSeparator = csvFileSetting.stringSeparator();

        if (row.contains(stringSeparator)) {
            String[] split = row.split(stringSeparator);

            for (int i = 1; i < split.length; i += 2) {
                split[i] = split[i].replaceAll(columnSeparator, TEMP_COLUMN_SEPARATOR_REPLACER);
            }

            var joined = String.join(EMPTY_STRING, split);
            split = joined.split(columnSeparator);

            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].replaceAll(TEMP_COLUMN_SEPARATOR_REPLACER, columnSeparator);
            }

            return split;
        }

        return row.split(columnSeparator);
    }

    public CsvRow getHeaders() {
        return headers;
    }

    public void reset() throws IOException {
        reader.reset();
        if (csvFileSetting.firsLineIsColumnNames()) {
            next();
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

}
