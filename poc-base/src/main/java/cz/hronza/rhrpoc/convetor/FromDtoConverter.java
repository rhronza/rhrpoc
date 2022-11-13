package cz.hronza.rhrpoc.convetor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * convert from DTO_TYPE to  DOMAIN_TYPE
 *
 * @param <DTO_TYPE>    input type
 * @param <DOMAIN_TYPE> ouput type
 */

public interface FromDtoConverter<DTO_TYPE, DOMAIN_TYPE> {
    // row -> row
    DOMAIN_TYPE fromDto(DTO_TYPE dto);

    //rows -> rows
    default List<DOMAIN_TYPE> fromDtos(Collection<DTO_TYPE> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        } else {
//            return dtos.stream().map(e -> fromDto(e)).collect(Collectors.toList());
            return dtos.stream().map(this::fromDto).collect(Collectors.toList());
        }
    }
}
