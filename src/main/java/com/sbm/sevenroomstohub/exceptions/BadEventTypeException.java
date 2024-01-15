package com.sbm.sevenroomstohub.exceptions;

public class BadEventTypeException extends RuntimeException {

    public BadEventTypeException(String errorMessage) {
        super(errorMessage);
    }
}
