package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.domain.DateTimeDays;
import cz.hronza.rhrpoc.business_logic.domain.DateTimeNew;
import cz.hronza.rhrpoc.business_logic.service.OffsetDateTimeService;
import org.springframework.stereotype.Service;

@Service
public class OffsetDateTimeFacadeImpl implements OffsetDateTimeFacade {
    private final OffsetDateTimeService offsetDateTimeService;

    public OffsetDateTimeFacadeImpl(OffsetDateTimeService offsetDateTimeService) {
        this.offsetDateTimeService = offsetDateTimeService;
    }

    @Override
    public DateTimeNew plusDaysForOffsetdatetime(DateTimeDays dateTimeDays) {
        return offsetDateTimeService.plusDaysForOffsetdatetime(dateTimeDays);
    }
}
