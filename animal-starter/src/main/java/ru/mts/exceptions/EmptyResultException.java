package ru.mts.exceptions;

public class EmptyResultException extends IllegalStateException {

    public EmptyResultException(String message) {
        super(message);
    }
}
