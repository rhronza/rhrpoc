package cz.hronza.rhrpoc.business_logic.domain;

import java.time.OffsetDateTime;
import java.util.StringJoiner;

public class DateTimeNew {
    private OffsetDateTime offsetDateTime;

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public DateTimeNew setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DateTimeNew.class.getSimpleName() + "[", "]")
                .add("offsetDateTime=" + offsetDateTime)
                .toString();
    }
}
