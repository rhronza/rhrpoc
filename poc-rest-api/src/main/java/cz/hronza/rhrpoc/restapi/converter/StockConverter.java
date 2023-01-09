package cz.hronza.rhrpoc.restapi.converter;

import cz.hronza.rhrpoc.business_logic.domain.Stock;
import cz.hronza.rhrpoc.converter.ToDomainConverter;
import cz.hronza.rhrpoc.core.api.dto.StockDtoRec;
import org.springframework.stereotype.Component;

@Component
public class StockConverter implements ToDomainConverter<StockDtoRec, Stock> {
    @Override
    public Stock toDomain(StockDtoRec dto) {
        return new Stock(
                dto.title(),
                dto.area(),
                dto.itemIds()
        );
    }
}
