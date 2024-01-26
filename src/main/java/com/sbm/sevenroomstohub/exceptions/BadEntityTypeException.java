package com.sbm.sevenroomstohub.exceptions;

public class BadEntityTypeException extends RuntimeException {

    public BadEntityTypeException(String errorMessage) {
        super(errorMessage);
    }
}
