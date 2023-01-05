package cz.hronza.rhrpoc.restapi.converter;

import cz.hronza.rhrpoc.business_logic.domain.StockIds;
import cz.hronza.rhrpoc.converter.ToDomainConverter;
import org.springframework.stereotype.Component;

@Component
public class StockTitleRecConverter implements ToDomainConverter<Long, StockIds> {
    @Override
    public StockIds toDomain(Long dto) {
        return new StockIds(dto);
    }


}
