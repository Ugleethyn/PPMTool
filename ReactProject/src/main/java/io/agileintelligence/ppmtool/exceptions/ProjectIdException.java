package io.agileintelligence.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException{

    /**
     * Custom exception to handle the duplicate inserts.
     * It is called by our Service to throw a new exception and show the correct message
     * @param message
     * Parameter is the message that we want to show as error message
     */
    public ProjectIdException(String message) {
        super(message);
    }
}
