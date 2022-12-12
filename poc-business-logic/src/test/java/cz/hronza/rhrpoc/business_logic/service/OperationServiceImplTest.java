package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.core.common.exception.RhrCannotBeDividedByZero;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {

    @InjectMocks
    private OperationServiceImpl operationService;

    @Test
    @Disabled("dát enable až bude pryč to volání repository")
    void sumTest() {
        int sum = operationService.sum(15, 3);
        assertEquals(18, sum);
    }

    @Test
    void differenceTest() {
        int difference = operationService.difference(15, 3);
        assertEquals(12, difference);
    }

    @Test
    void divideTest() {
        int divide = operationService.divide(15, 3);
        assertEquals(5, divide);
    }

    @Test
    void divideNegativeTest() {
        RhrCannotBeDividedByZero rhrCannotBeDividedByZero =
                assertThrows(RhrCannotBeDividedByZero.class, () -> operationService.divide(15, 0));
        assertEquals(OperationServiceImpl.CANNOT_BE_DIVIDED_BY_0_MESSAGE, rhrCannotBeDividedByZero.getMessage());
    }

    @Test
    void multiplicationTest() {
        int multiplication = operationService.multiplication(15, 3);
        assertEquals(45, multiplication);
    }
}
