package org.kolpashchikov.csv.domain.structure;

public class CsvColumn {

    private final String value;

    public CsvColumn(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

    @SuppressWarnings("unchecked")
    public <T> T as(T clazz) {
        try {
            return (T) value;
        } catch (Exception e) {
            return null; // todo exception
        }
    }

}
