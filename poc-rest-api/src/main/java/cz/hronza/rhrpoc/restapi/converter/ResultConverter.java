package cz.hronza.rhrpoc.restapi.converter;

import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.converter.ToDtoConverter;
import cz.hronza.rhrpoc.core.api.dto.ResultRecDto;
import org.springframework.stereotype.Component;

@Component
public class ResultConverter implements ToDtoConverter<Result, ResultRecDto> {
    @Override
    public ResultRecDto toDto(Result domain) {
        return new ResultRecDto(
                domain.getOperationsEnum() != null ?
                        domain.getOperationsEnum().toString() :
                        domain.getMultipleOperationsEnum().toString()
                , String.valueOf(domain.getResult()));
    }
}
