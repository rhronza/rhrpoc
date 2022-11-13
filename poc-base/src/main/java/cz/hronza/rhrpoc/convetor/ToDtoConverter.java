package cz.hronza.rhrpoc.convetor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * convert from DOMAIN_TYPE to DTO_TYPE 
 *
 * @param <DOMAIN_TYPE> input type
 * @param <DTO_TYPE> output Dto type
 */

public interface ToDtoConverter<DOMAIN_TYPE, DTO_TYPE> {
    DTO_TYPE toDto(DOMAIN_TYPE domain); // nutná implementace row -> row

    // může se používat pokud je implementována metoda toDto !
    default List<DTO_TYPE> toDtos(Collection<DOMAIN_TYPE> domainList) {
        if (domainList == null) {
            return Collections.emptyList();
        } else {
//            return domainList.stream().map(e -> toDto(e)).collect(Collectors.toList());
            return domainList.stream().map(this::toDto).collect(Collectors.toList());
        }

    }


}
