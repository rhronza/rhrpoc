package cz.hronza.rhrpoc.restapi.converter;

import cz.hronza.rhrpoc.business_logic.domain.Result;
import cz.hronza.rhrpoc.convetor.ToDtoConverter;
import cz.hronza.rhrpoc.core.api.dto.ResultDto;
import org.springframework.stereotype.Component;

@Component
public class ResultConverter implements ToDtoConverter<Result, ResultDto> {
    @Override
    public ResultDto toDto(Result domain) {
        return new ResultDto()
                .setResult(String.valueOf(domain.getResult()))
                .setOperationsEnum(domain.getOperationsEnum())
                ;
    }
}
