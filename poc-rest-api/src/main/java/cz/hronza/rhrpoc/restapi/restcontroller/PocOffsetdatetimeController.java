package cz.hronza.rhrpoc.restapi.restcontroller;

import cz.hronza.rhrpoc.business_logic.domain.DateTimeDays;
import cz.hronza.rhrpoc.business_logic.domain.DateTimeNew;
import cz.hronza.rhrpoc.business_logic.facade.OffsetDateTimeFacade;
import cz.hronza.rhrpoc.core.api.api.PocRestApiOffsetdatetime;
import cz.hronza.rhrpoc.core.api.dto.OffsetDateTimeOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class PocOffsetdatetimeController implements PocRestApiOffsetdatetime {
    private final OffsetDateTimeFacade offsetDateTimeFacade;

    public PocOffsetdatetimeController(OffsetDateTimeFacade offsetDateTimeFacade) {
        this.offsetDateTimeFacade = offsetDateTimeFacade;
    }

    @Override
    public ResponseEntity<OffsetDateTimeOutputDto> plusDaysForOffsetdatetime(OffsetDateTime offsetDateTime, Integer days) {
        DateTimeNew dateTimeNew = offsetDateTimeFacade.plusDaysForOffsetdatetime(new DateTimeDays().setOffsetDateTime(offsetDateTime).setDays(days));
        return ResponseEntity.ok(new OffsetDateTimeOutputDto(dateTimeNew.getOffsetDateTime()));
    }
}
