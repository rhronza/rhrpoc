package cz.hronza.rhrpoc.business_logic.domain;

import java.time.OffsetDateTime;
import java.util.StringJoiner;

public class DateTimeDays {
    private OffsetDateTime offsetDateTime;
    private Integer days;

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public DateTimeDays setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
        return this;
    }

    public Integer getDays() {
        return days;
    }

    public DateTimeDays setDays(Integer days) {
        this.days = days;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DateTimeDays.class.getSimpleName() + "[", "]")
                .add("offsetDateTime=" + offsetDateTime)
                .add("days=" + days)
                .toString();
    }
}
