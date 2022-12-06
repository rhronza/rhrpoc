package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.domain.DateTimeDays;
import cz.hronza.rhrpoc.business_logic.domain.DateTimeNew;

import javax.validation.Valid;

@Valid
public interface OffsetDateTimeFacade {
    DateTimeNew plusDaysForOffsetdatetime(DateTimeDays dateTimeDays);
}
