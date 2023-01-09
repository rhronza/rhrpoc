package cz.hronza.rhrpoc.restapi.converter;

import cz.hronza.rhrpoc.business_logic.domain.StockItemsMovementsRec;
import cz.hronza.rhrpoc.converter.ToDtoConverter;
import cz.hronza.rhrpoc.core.api.dto.MovementDtoRec;
import cz.hronza.rhrpoc.core.api.dto.StockItemsMovementsRecDto;
import cz.hronza.rhrpoc.core.api.dto.StockItemsRecDto;
import org.springframework.stereotype.Component;

@Component
public class StockItemsMovementsDtoRecConverter implements ToDtoConverter<StockItemsMovementsRec, StockItemsMovementsRecDto> {
    @Override
    public StockItemsMovementsRecDto toDto(StockItemsMovementsRec domain) {
        return new StockItemsMovementsRecDto(
                domain.id(),
                domain.title(),
                domain.area(),
                domain.stockItemsRecs()
                        .stream()
                        .map(stockItem -> new StockItemsRecDto(
                                stockItem.storedItemTitle(),
                                stockItem.storedItemDescription(),
                                stockItem.stockId(),
                                stockItem.storedItemId(),
                                stockItem.currenAmount(),
                                stockItem.minimalAmount(),
                                stockItem.dateLastStocking(),
                                stockItem.dateTimeLastIssue(),
                                stockItem.movementRecs()
                                        .stream()
                                        .map(movement -> new MovementDtoRec(
                                                movement.stockId(),
                                                movement.storedItemId(),
                                                movement.amount(),
                                                movement.created()
                                        )).toList()
                        ))
                        .toList()
        );
    }
}
