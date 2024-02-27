package ru.mts.models.enums;

public enum AnimalType {

    CAT("CAT"),
    DOG("DOG"),
    SHARK("SHARK"),
    WOLF("WOLF");

    private final String value;

    AnimalType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
