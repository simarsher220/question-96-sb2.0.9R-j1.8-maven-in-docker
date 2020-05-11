package org.codejudge.sb.error.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends Exception {

    private HttpStatus status;

    public GenericException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
