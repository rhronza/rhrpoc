package cz.hronza.restapi.restcontroller;

import cz.hronza.business_logic.enumer.OperationsEnum;
import cz.hronza.business_logic.facade.CalculationFacade;
import cz.hronza.restapi.api.PocRestApi;
import cz.hronza.restapi.dto.ResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//@Api
@RestController
public class PocController implements PocRestApi {
    private final CalculationFacade calculationFacade;
    private static final System.Logger logger = System.getLogger(PocController.class.getSimpleName());


    public PocController(CalculationFacade calculationFacade) {
        this.calculationFacade = calculationFacade;
    }


    @Override
    public ResponseEntity<ResultDto> makeOperation(Integer variableA,
                                                   Integer variableB,
                                                   OperationsEnum operationsEnum) {
        int result = calculationFacade.calculate(variableA, variableB, operationsEnum);

        logger.log(
                System.Logger.Level.INFO,
                String.format(
                        "makeOperation is running, operation=%s, variableA=%d, variableB=%d, result=%d",
                        operationsEnum.toString(), variableA, variableB, result));

        return ResponseEntity.ok(new ResultDto()
                .setOperationsEnum(operationsEnum)
                .setVariableA(variableA)
                .setVariableB(variableB)
                .setResult(String.valueOf(result)));
    }
}
