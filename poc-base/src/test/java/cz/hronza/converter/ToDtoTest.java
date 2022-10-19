package cz.hronza.converter;

import cz.hronza.common.Person;
import cz.hronza.common.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ToDtoTest {

    private static final String ROMAN = "Roman";
    private static final String HRONZA = "Hronza";
    private static final String HRONZOVAA = "Hronzová";
    private static final String MONIKA = "Monika";
    private PersonDtoConverter personDtoConverter;
    List<Person> people;

    @BeforeEach
    public void init() {
        this.personDtoConverter = new PersonDtoConverter();
        this.people = List.of(
                new Person().setFirstName(ROMAN).setSurname(HRONZA).setAge(333),
                new Person().setFirstName(MONIKA).setSurname(HRONZOVAA).setAge(18));
    }

    @Test
    void convertorToDtoTest() {
        List<PersonDto> personDtos = personDtoConverter.toDtos(people);
        assertEquals(2, personDtos.size());
        assertPersonDtos(personDtos);
    }

    @Test
    void mapConvertorTest() {
        List<PersonDto> personDtos = people.stream().map(e -> {
            PersonDto dto = new PersonDto();
            dto.setName(new ArrayList<>());
            dto.getName().add(e.getFirstName());
            dto.getName().add(e.getSurname());
            return dto;
        }).collect(Collectors.toList());

        assertPersonDtos(personDtos);
    }

    private static void assertPersonDtos(List<PersonDto> personDtos) {
        PersonDto personDtoRow1 = personDtos.get(0);
        assertEquals(ROMAN, personDtoRow1.getName().get(0));
        assertEquals(HRONZA, personDtoRow1.getName().get(1));
        PersonDto personDtoRow2 = personDtos.get(1);
        assertEquals(MONIKA, personDtoRow2.getName().get(0));
        assertEquals(HRONZOVAA, personDtoRow2.getName().get(1));
    }


}
