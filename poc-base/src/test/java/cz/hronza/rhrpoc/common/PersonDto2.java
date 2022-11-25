package cz.hronza.rhrpoc.common;

public class PersonDto2 {
    private String name;
    private String rodneCislo;

    public String getName() {
        return name;
    }

    public PersonDto2 setName(String name) {
        this.name = name;
        return this;
    }

    public String getRodneCislo() {
        return rodneCislo;
    }

    public PersonDto2 setRodneCislo(String rodneCislo) {
        this.rodneCislo = rodneCislo;
        return this;
    }
}
