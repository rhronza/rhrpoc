package cz.hronza.rhrpoc.business_logic.domain;

import java.util.List;

public record Stock(
        String title,
        Integer area ,
        List<Long> itemIds
) {
}
