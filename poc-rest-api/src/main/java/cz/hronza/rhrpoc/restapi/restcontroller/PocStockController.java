package cz.hronza.rhrpoc.restapi.restcontroller;

import cz.hronza.rhrpoc.business_logic.facade.StockFacade;
import cz.hronza.rhrpoc.core.api.api.PocRestApiStock;
import cz.hronza.rhrpoc.core.api.dto.NewStockId;
import cz.hronza.rhrpoc.core.api.dto.StockDtoRec;
import cz.hronza.rhrpoc.core.api.dto.StockItemsMovementsListRecDto;
import cz.hronza.rhrpoc.restapi.converter.StockConverter;
import cz.hronza.rhrpoc.restapi.converter.StockItemsMovementsDtoRecConverter;
import cz.hronza.rhrpoc.restapi.converter.StockTitleRecConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class PocStockController implements PocRestApiStock {

    private final StockFacade stockFacade;
    private final StockTitleRecConverter stockTitleRecConverter;
    private final StockConverter stockConverter;

    private final StockItemsMovementsDtoRecConverter stockItemsMovementsDtoRecConverter;

    public PocStockController(StockFacade stockFacade,
                              StockTitleRecConverter stockTitleRecConverter,
                              StockConverter stockConverter,
                              StockItemsMovementsDtoRecConverter stockItemsMovementsDtoRecConverter) {
        this.stockFacade = stockFacade;
        this.stockTitleRecConverter = stockTitleRecConverter;
        this.stockConverter = stockConverter;
        this.stockItemsMovementsDtoRecConverter = stockItemsMovementsDtoRecConverter;
    }

    @Override
    public ResponseEntity<NewStockId> addNewStock(StockDtoRec stockDtoRec) {
        // String niceJson = new ObjectMapper().registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(stockDtoRec) ;
        NewStockId newStockId = new NewStockId(stockFacade.addNewStock(stockConverter.toDomain(stockDtoRec)));

        return new ResponseEntity<>(newStockId, CREATED);
    }

    @Override
    public ResponseEntity<StockItemsMovementsListRecDto> getStockItemsAndMovements(List<Long> stockTitles) {
        StockItemsMovementsListRecDto stockItemsMovementsListDtoRec =
                new StockItemsMovementsListRecDto(
                        stockItemsMovementsDtoRecConverter.toDtos(
                                stockFacade.getStockItemsAndMovements(
                                        stockTitleRecConverter.toDomains(stockTitles))));
        return ResponseEntity.ok(stockItemsMovementsListDtoRec);
    }
}
