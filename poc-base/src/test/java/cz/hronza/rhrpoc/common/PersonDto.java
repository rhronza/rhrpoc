package cz.hronza.rhrpoc.common;

import java.util.List;

public class PersonDto {
    private List<String> name;
    private String rodneCislo;

    public List<String> getName() {
        return name;
    }

    public PersonDto setName(List<String> name) {
        this.name = name;
        return this;
    }

    public String getRodneCislo() {
        return rodneCislo;
    }

    public PersonDto setRodneCislo(String rodneCislo) {
        this.rodneCislo = rodneCislo;
        return this;
    }
}
