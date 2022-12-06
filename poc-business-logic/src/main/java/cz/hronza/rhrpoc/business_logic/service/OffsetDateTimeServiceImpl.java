package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.business_logic.domain.DateTimeDays;
import cz.hronza.rhrpoc.business_logic.domain.DateTimeNew;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class OffsetDateTimeServiceImpl implements OffsetDateTimeService {
    @Override
    public DateTimeNew plusDaysForOffsetdatetime(DateTimeDays dateTimeDays) {
        OffsetDateTime offsetDateTime = dateTimeDays.getOffsetDateTime();
        Integer days = dateTimeDays.getDays();
        if (offsetDateTime != null && days != null) {
            return new DateTimeNew().setOffsetDateTime(offsetDateTime.plusDays(days));
        } else if (offsetDateTime != null) {
            return new DateTimeNew().setOffsetDateTime(offsetDateTime);
        } else return new DateTimeNew().setOffsetDateTime(null);
    }
}
