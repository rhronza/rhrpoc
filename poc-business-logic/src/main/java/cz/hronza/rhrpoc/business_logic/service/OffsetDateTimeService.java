package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.business_logic.domain.DateTimeDays;
import cz.hronza.rhrpoc.business_logic.domain.DateTimeNew;

public interface OffsetDateTimeService {
    DateTimeNew plusDaysForOffsetdatetime(DateTimeDays dateTimeDays);
}
