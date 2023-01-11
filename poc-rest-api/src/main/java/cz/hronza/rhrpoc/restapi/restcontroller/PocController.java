package cz.hronza.rhrpoc.restapi.restcontroller;

import cz.hronza.rhrpoc.business_logic.domain.DateTimeDays;
import cz.hronza.rhrpoc.business_logic.domain.DateTimeNew;
import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.business_logic.facade.CalculationFacade;
import cz.hronza.rhrpoc.business_logic.facade.ClientEasyBeFacade;
import cz.hronza.rhrpoc.business_logic.facade.OffsetDateTimeFacade;
import cz.hronza.rhrpoc.business_logic.facade.StockFacade;
import cz.hronza.rhrpoc.core.api.api.PocRestApi;
import cz.hronza.rhrpoc.core.api.dto.NewStockId;
import cz.hronza.rhrpoc.core.api.dto.OffsetDateTimeOutputDto;
import cz.hronza.rhrpoc.core.api.dto.OutputDto;
import cz.hronza.rhrpoc.core.api.dto.ResultRecDto;
import cz.hronza.rhrpoc.core.api.dto.StockDtoRec;
import cz.hronza.rhrpoc.core.api.dto.StockItemsMovementsListRecDto;
import cz.hronza.rhrpoc.core.common.enums.MultipleOperationsEnum;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import cz.hronza.rhrpoc.restapi.converter.ResultConverter;
import cz.hronza.rhrpoc.restapi.converter.StockConverter;
import cz.hronza.rhrpoc.restapi.converter.StockItemsMovementsDtoRecConverter;
import cz.hronza.rhrpoc.restapi.converter.StockTitleRecConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class PocController implements PocRestApi {
    private final ClientEasyBeFacade clientEasyBeFacade;
    private final CalculationFacade calculationFacade;
    private final OffsetDateTimeFacade offsetDateTimeFacade;
    private final StockFacade stockFacade;
    private final StockTitleRecConverter stockTitleRecConverter;
    private final StockConverter stockConverter;

    private final StockItemsMovementsDtoRecConverter stockItemsMovementsDtoRecConverter;

    private final ResultConverter resultConverter;
    private final Logger log = LoggerFactory.getLogger(PocController.class);


    public PocController(CalculationFacade calculationFacade,
                         ClientEasyBeFacade clientEasyBeFacade,
                         OffsetDateTimeFacade offsetDateTimeFacade,
                         StockFacade stockFacade,
                         StockTitleRecConverter stockTitleRecConverter,
                         StockConverter stockConverter,
                         StockItemsMovementsDtoRecConverter stockItemsMovementsDtoRecConverter,
                         ResultConverter resultConverter) {
        this.calculationFacade = calculationFacade;
        this.clientEasyBeFacade = clientEasyBeFacade;
        this.offsetDateTimeFacade = offsetDateTimeFacade;
        this.stockFacade = stockFacade;
        this.stockTitleRecConverter = stockTitleRecConverter;
        this.stockConverter = stockConverter;
        this.stockItemsMovementsDtoRecConverter = stockItemsMovementsDtoRecConverter;
        this.resultConverter = resultConverter;
    }


    @Override
    public ResponseEntity<ResultRecDto> makeOperation(Integer variableA,
                                                      Integer variableB,
                                                      OperationsEnum operationsEnum) {
        Result result = calculationFacade.calculate(variableA, variableB, operationsEnum);

        log.info("makeOperation is running, operation={}, variableA={}, variableB={}, result={}",
                operationsEnum, variableA, variableB, result.getResult());

        return ResponseEntity.ok(resultConverter.toDto(result));
    }

    @Override
    public ResponseEntity<ResultRecDto> makeMultipleOperation(@Valid MultipleOperationsEnum multipleOperationsEnum, @Valid List<Integer> numbers) {
        Result result = calculationFacade.multipleCaculation(multipleOperationsEnum, numbers);
        return ResponseEntity.ok(resultConverter.toDto(result));
    }

    @Override
    public ResponseEntity<NewStockId> addNewStock(StockDtoRec stockDtoRec) {
        // String niceJson = new ObjectMapper().registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(stockDtoRec) ;
        NewStockId newStockId = new NewStockId(stockFacade.addNewStock(stockConverter.toDomain(stockDtoRec)));

        return new ResponseEntity<>(newStockId, CREATED);
    }

    @Override
    public ResponseEntity<OutputDto> reverseEndpointFromEasyBe(String id, String name) {
        log.info("START RHRPOC, id={}, name ={}", id, name);
        ResponseEntity<OutputDto> outputDtoResponseEntity = clientEasyBeFacade.reverseEndpointFromEasyBe(id, name);
        log.info("  output={}, END RHRPOC", outputDtoResponseEntity);
        return outputDtoResponseEntity;
    }

    @Override
    public ResponseEntity<OffsetDateTimeOutputDto> plusDaysForOffsetdatetime(OffsetDateTime offsetDateTime, Integer days) {
        DateTimeNew dateTimeNew = offsetDateTimeFacade.plusDaysForOffsetdatetime(new DateTimeDays().setOffsetDateTime(offsetDateTime).setDays(days));
        return ResponseEntity.ok(new OffsetDateTimeOutputDto(dateTimeNew.getOffsetDateTime()));
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
