package cz.hronza.rhrpoc.restapi.restcontroller;

import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.business_logic.facade.CalculationFacade;
import cz.hronza.rhrpoc.core.api.api.PocRestApi;
import cz.hronza.rhrpoc.core.api.dto.ResultDto;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import cz.hronza.rhrpoc.restapi.converter.ResultConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//@Api
@RestController
public class PocController implements PocRestApi {
    private final CalculationFacade calculationFacade;

    private final ResultConverter resultConverter;
    private static final System.Logger logger = System.getLogger(PocController.class.getSimpleName());


    public PocController(CalculationFacade calculationFacade, ResultConverter resultConverter) {
        this.calculationFacade = calculationFacade;
        this.resultConverter = resultConverter;
    }


    @Override
    public ResponseEntity<ResultDto> makeOperation(Integer variableA,
                                                   Integer variableB,
                                                   OperationsEnum operationsEnum) {
        Result result = calculationFacade.calculate(variableA, variableB, operationsEnum);

        logger.log(
                System.Logger.Level.INFO,
                String.format(
                        "makeOperation is running, operation=%s, variableA=%d, variableB=%d, result=%d",
                        operationsEnum.toString(), variableA, variableB, result.getResult()));

        return ResponseEntity.ok(resultConverter.toDto(result));
    }
}
