package cz.hronza.rhrpoc.converter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * convert from SOURCE to TARGET
 *
 * @param <S> input type (source)
 * @param <T> output Dto type (target)
 */

public interface ToDtoConverter<S, T> {
    T toDto(S domain); // nutná implementace row -> row

    default List<T> toDtos(Collection<S> domainList) {
        if (domainList == null) {
            return Collections.emptyList();
        } else {
            return domainList.stream().map(this::toDto).toList();
        }

    }


}
