package cz.hronza.rhrpoc.restapi.restcontroller;

import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.business_logic.facade.CalculationFacade;
import cz.hronza.rhrpoc.core.api.api.PocRestApiCalculation;
import cz.hronza.rhrpoc.core.api.dto.ResultRecDto;
import cz.hronza.rhrpoc.core.common.enums.MultipleOperationsEnum;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import cz.hronza.rhrpoc.restapi.converter.ResultConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PocCalculationController implements PocRestApiCalculation {

    private final CalculationFacade calculationFacade;

    private final ResultConverter resultConverter;
    private final Logger log = LoggerFactory.getLogger(PocCalculationController.class);


    public PocCalculationController(CalculationFacade calculationFacade,
                                    ResultConverter resultConverter) {
        this.calculationFacade = calculationFacade;
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

}
