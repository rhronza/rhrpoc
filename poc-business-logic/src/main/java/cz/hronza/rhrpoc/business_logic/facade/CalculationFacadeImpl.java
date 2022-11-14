package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.business_logic.service.OperationService;
import cz.hronza.rhrpoc.business_logic.enumer.OperationsEnum;
import org.springframework.stereotype.Service;

@Service
public class CalculationFacadeImpl implements CalculationFacade {

    private final OperationService operationService;

    public CalculationFacadeImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public Result calculate(Integer a, Integer b, OperationsEnum operationsEnum) {
        int result = 99;
        switch (operationsEnum) {
            case SUM:
                result = operationService.sum(a, b);
                break;
            case DIFFERENCE:
                result = operationService.difference(a, b);
                break;
            case DIVIDE:
                result = operationService.divide(a, b);
                break;
            case MULTIPLICATION:
                result = operationService.multiplication(a, b);
                break;
        }
        return new Result().setVarA(a).setVarB(b).setOperationsEnum(operationsEnum).setResult( result);
    }
}
