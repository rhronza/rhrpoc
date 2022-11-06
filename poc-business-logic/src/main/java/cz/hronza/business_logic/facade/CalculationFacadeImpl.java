package cz.hronza.business_logic.facade;

import cz.hronza.business_logic.service.OperationService;
import cz.hronza.business_logic.enumer.OperationsEnum;
import org.springframework.stereotype.Service;

@Service
public class CalculationFacadeImpl implements CalculationFacade {

    private final OperationService operationService;

    public CalculationFacadeImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public int calculate(Integer a, Integer b, OperationsEnum operationsEnum) {
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
        return result;
    }
}
