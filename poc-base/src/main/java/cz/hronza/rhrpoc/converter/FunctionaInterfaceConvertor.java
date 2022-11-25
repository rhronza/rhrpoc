package cz.hronza.rhrpoc.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface FunctionaInterfaceConvertor {
    static <O, I> List<O> listToList(Supplier<O> getter, BiConsumer<O, I> setter, List<I> list) {
        if (list != null) {
            return list
                    .stream()
                    .map(e -> elementConvert(getter, setter, e))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } else return new ArrayList<>();
    }

    static <O, I> List<O> elementToList(Supplier<O> getter, BiConsumer<O, I> setter, I element) {
        O out = elementConvert(getter, setter, element);
        if (out != null) {
            return Stream.of(out).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    static <O, I> O elementConvert(Supplier<O> gettter, BiConsumer<O, I> setter, I element) {
        if (element != null) {
            var out = gettter.get();
            setter.accept(out, element);
            return out;
        } else return null;
    }
}
