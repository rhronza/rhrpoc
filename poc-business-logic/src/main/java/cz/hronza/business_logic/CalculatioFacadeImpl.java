package cz.hronza.business_logic;

import cz.hronza.business_logic.enumer.OperationsEnum;
import org.springframework.stereotype.Service;

@Service
public class CalculatioFacadeImpl implements CalculationFacade {

    private final Operation operation;

    public CalculatioFacadeImpl(Operation operation) {
        this.operation = operation;
    }

    @Override
    public int calculate(Integer a, Integer b, OperationsEnum operationsEnum) {
        int result = 0;
        switch (operationsEnum) {
            case SUM:
                result = operation.sum(a, b);
            case DIFFERENCE:
                result = operation.difference(a, b);
            case DIVIDE:
                result = operation.divide(a, b);
            case MULTIPLICATION:
                result = operation.multiplication(a, b);
        }
        return result;
    }
}
