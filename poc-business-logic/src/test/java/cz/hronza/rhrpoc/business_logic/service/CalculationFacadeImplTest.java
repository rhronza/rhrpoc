package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.business_logic.facade.CalculationFacadeImpl;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculationFacadeImplTest {
    private static final int A = 10;
    private static final int B = 20;
    private static final int SUM_EXPECTED = 35;
    private static final int DIVIDE_EXPECTED = 18;
    private static final int DIFFERENCE_EXPECTED = 33;

    @Mock
    private OperationServiceImpl operationServiceImpl;

    @InjectMocks
    private CalculationFacadeImpl calculationFacade;

    @Test
    void calculateSumTest() {
        when(operationServiceImpl.sum(any(), any())).thenReturn(SUM_EXPECTED);
        Result currentValue = calculationFacade.calculate(A, B, OperationsEnum.SUM);
        assertEquals(SUM_EXPECTED, currentValue.getResult());
    }

    @Test
    void calculateDivideTest() {
        when(operationServiceImpl.divide(any(), any())).thenReturn(DIVIDE_EXPECTED);
        Result calculate = calculationFacade.calculate(A, B, OperationsEnum.DIVIDE);
        assertEquals(DIVIDE_EXPECTED, calculate.getResult());
    }

    @Test
    void calculateDifferenceTest() {
        when(operationServiceImpl.difference(A, B)).thenReturn(DIFFERENCE_EXPECTED);
        Result calculate = calculationFacade.calculate(A, B, OperationsEnum.DIFFERENCE);
        assertEquals(DIFFERENCE_EXPECTED, calculate.getResult());
    }
}
