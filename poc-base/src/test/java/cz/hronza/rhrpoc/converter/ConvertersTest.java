package cz.hronza.rhrpoc.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.hronza.rhrpoc.common.NameDto;
import cz.hronza.rhrpoc.common.Person;
import cz.hronza.rhrpoc.common.PersonDto;
import cz.hronza.rhrpoc.common.PersonDto2;
import cz.hronza.rhrpoc.utils.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cz.hronza.rhrpoc.converter.FunctionaInterfaceConvertor.elementToList;
import static cz.hronza.rhrpoc.converter.FunctionaInterfaceConvertor.elementConvert;
import static cz.hronza.rhrpoc.converter.FunctionaInterfaceConvertor.listToList;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ConvertersTest {

    private static final String ROMAN_NAME = "Roman";
    private static final String HRONZA = "Hronza";
    private static final String HRONZOVAA = "Hronzová";
    private static final String MONIKA = "Monika";
    public static final String KAREL_NAME = "Karel";
    public static final String PAVEL_NAME = "Pavel";
    public static final String RODNE_CISLO = "999999/9999";
    private PersonDtoConverter personDtoConverter;
    List<Person> people;
    List<String> names;

    @BeforeEach
    public void init() {
        this.personDtoConverter = new PersonDtoConverter();

        this.people = List.of(new Person().setFirstName(ROMAN_NAME).setSurname(HRONZA).setAge(333), new Person().setFirstName(MONIKA).setSurname(HRONZOVAA).setAge(18));
        this.names = List.of(KAREL_NAME, PAVEL_NAME);

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
        assertEquals(ROMAN_NAME, personDtoRow1.getName().get(0));
        assertEquals(HRONZA, personDtoRow1.getName().get(1));
        PersonDto personDtoRow2 = personDtos.get(1);
        assertEquals(MONIKA, personDtoRow2.getName().get(0));
        assertEquals(HRONZOVAA, personDtoRow2.getName().get(1));
    }

    @Test
    void jsonConvertToObject() throws JsonProcessingException {

        String jsonFromObject = JsonConverter.toJsonFromObject(people);

        JsonNode jsonNode = new ObjectMapper().readTree(jsonFromObject);

        assertEquals(2, jsonNode.size());

        JsonNode jsonNodeRow1 = jsonNode.get(0);
        assertEquals(ROMAN_NAME, jsonNodeRow1.get("firstName").asText());
        assertEquals(HRONZA, jsonNodeRow1.get("surname").asText());
        assertEquals(333, jsonNodeRow1.get("age").asInt());

        JsonNode jsonNodeRow2 = jsonNode.get(1);
        assertEquals(MONIKA, jsonNodeRow2.get("firstName").asText());
        assertEquals(HRONZOVAA, jsonNodeRow2.get("surname").asText());
        assertEquals(18, jsonNodeRow2.get("age").asInt());
    }

    @Test
    void listToListTest() {

        List<NameDto> nameDtos = listToList(NameDto::new, NameDto::setNameDto, this.names);
        assertEquals(2, nameDtos.size());
        assertEquals(KAREL_NAME, nameDtos.get(0).getNameDto());
        assertEquals(PAVEL_NAME, nameDtos.get(1).getNameDto());
    }

    @Test
    void listToListTest2Attributes() {
        List<PersonDto2> personsDto2 = listToList(PersonDto2::new, (dto, name) -> {
            dto.setName(name);
            dto.setRodneCislo(RODNE_CISLO);
        }, this.names);

        assertEquals(2, personsDto2.size());
        assertEquals(KAREL_NAME, personsDto2.get(0).getName());
        assertEquals(RODNE_CISLO, personsDto2.get(0).getRodneCislo());
        assertEquals(PAVEL_NAME, personsDto2.get(1).getName());
        assertEquals(RODNE_CISLO, personsDto2.get(1).getRodneCislo());
    }

    @Test
    void elementToListConverter() {
        List<PersonDto2> personDto2s = elementToList(PersonDto2::new, PersonDto2::setName, ROMAN_NAME);
        assertEquals(1, personDto2s.size());
        assertEquals(ROMAN_NAME, personDto2s.get(0).getName());
    }

    @Test
    void elementConvertTest() {
        PersonDto2 personDto2 = elementConvert(PersonDto2::new, PersonDto2::setName, ROMAN_NAME);
        assertEquals(ROMAN_NAME, personDto2.getName());
    }
}
