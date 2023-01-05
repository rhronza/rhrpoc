package cz.hronza.rhrpoc.business_logic.domain;

import java.time.OffsetDateTime;

public record MovementRec(
        Long stockId,
        Long storedItemId,
        Float amount,
        OffsetDateTime created
) {
}
