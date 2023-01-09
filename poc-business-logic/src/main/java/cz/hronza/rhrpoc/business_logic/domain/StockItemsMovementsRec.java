package cz.hronza.rhrpoc.business_logic.domain;

import java.util.List;

public record StockItemsMovementsRec(
        Long id,
        String title,
        Integer area,
        List<StockItemsRec>  stockItemsRecs
) {
}
