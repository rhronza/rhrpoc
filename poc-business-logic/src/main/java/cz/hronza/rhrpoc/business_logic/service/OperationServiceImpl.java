package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.core.common.exception.RhrPocCannotBeDividedByZero;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    public static final String CANNOT_BE_DIVIDED_BY_0_MESSAGE = "Cannot be divided by 0.";

    @Override
    public int sum(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public int difference(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public int divide(Integer a, Integer b) {
        if (b != 0)
            return a / b;
        else
            throw new RhrPocCannotBeDividedByZero(CANNOT_BE_DIVIDED_BY_0_MESSAGE, "message", "variableB is equals 0");
    }

    @Override
    public int multiplication(Integer a, Integer b) {
        return a * b;
    }
}
