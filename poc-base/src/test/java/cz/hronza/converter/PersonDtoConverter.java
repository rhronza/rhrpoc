package cz.hronza.converter;

import cz.hronza.common.Person;
import cz.hronza.common.PersonDto;
import cz.hronza.convetor.ToDtoConverter;

import java.time.LocalDate;
import java.util.List;

public class PersonDtoConverter implements ToDtoConverter<Person, PersonDto> {
    @Override
    public PersonDto toDto(Person domain) {
        int year = LocalDate.now().getYear() - domain.getAge();
        return new PersonDto()
                .setName(List.of(domain.getFirstName(), domain.getSurname()))
                .setRodneCislo(String.format("%dxxxxxx", year));
    }
}
