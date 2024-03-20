package ru.mts.exceptions;

public class IllegalBirthDateException extends IllegalArgumentException {

    public IllegalBirthDateException(String message) {
        super(message);
    }
}
