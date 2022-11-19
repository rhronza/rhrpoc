package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.core.common.enums.MultipleOperationsEnum;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CalculationFacade {
    Result calculate(Integer a, Integer b, OperationsEnum operations);

    Result multipleCaculation(MultipleOperationsEnum multipleOperationsEnum, List<Integer> numbers);
}
