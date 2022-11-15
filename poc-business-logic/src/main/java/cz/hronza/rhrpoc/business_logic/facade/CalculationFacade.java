package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.domain.Result;

import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CalculationFacade {
    Result calculate (Integer a, Integer b, OperationsEnum operations);
}
