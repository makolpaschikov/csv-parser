package org.kolpashchikov.csv;

import org.kolpashchikov.csv.domain.structure.CsvRow;

import java.io.Closeable;
import java.io.IOException;

public interface CsvParser extends Closeable {

    CsvRow next() throws IOException;

}
