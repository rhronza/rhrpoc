package cz.hronza.rhrpoc.business_logic.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record StockItemsRec(
        String storedItemTitle,
        String storedItemDescription,
        Long stockId,
        Long storedItemId,
        Long currenAmount,
        Long minimalAmount,
        LocalDate dateLastStocking,
        OffsetDateTime dateTimeLastIssue,
        List<MovementRec> movementRecs
) {
}
