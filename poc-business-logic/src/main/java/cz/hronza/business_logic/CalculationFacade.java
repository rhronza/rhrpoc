package cz.hronza.business_logic;

import cz.hronza.business_logic.enumer.OperationsEnum;

public interface CalculationFacade {
    int calculate (Integer a, Integer b, OperationsEnum operations);
}
