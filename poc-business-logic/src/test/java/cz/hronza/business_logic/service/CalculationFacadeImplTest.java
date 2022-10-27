package cz.hronza.business_logic.service;

import cz.hronza.business_logic.CalculationFacadeImpl;
import cz.hronza.business_logic.OperationServiceImpl;
import cz.hronza.business_logic.enumer.OperationsEnum;
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
    private static final int EXPECTED = 35;

    @Mock
    private OperationServiceImpl operationServiceImpl;

    @InjectMocks
    private CalculationFacadeImpl calculatioFacade;

    @Test
    void calculateSumTest() {

        when(operationServiceImpl.sum(any(), any())).thenReturn(EXPECTED);

        int currentValue = calculatioFacade.calculate(A, B, OperationsEnum.SUM);
        assertEquals(EXPECTED, currentValue);

    }
}
