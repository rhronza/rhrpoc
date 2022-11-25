package cz.hronza.rhrpoc.common;

public class Name {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Name name(String name) {
        this.name = name;
        return this;
    }


}
