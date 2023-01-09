package cz.hronza.rhrpoc.converter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * convert from SOURCE to TARGET
 *
 * @param <S>    input type (source)
 * @param <T> ouput type (target)
 */

public interface ToDomainConverter<S, T> {
    T toDomain(S dto);

    default List<T> toDomains(Collection<S> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        } else {
             return dtos.stream().map(this::toDomain).toList();
        }
    }
}
