package io.agileintelligence.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is a Controller that handles exceptions.
 * When another Controller throws an exception that this Controller can handle,
 *this Controller will give the answer to the user.
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * It takes the thrown exception and it gives the correct message of error type to the user,
     * depends the message we gave when we threw the new exception
     * @param ex
     * Is the exception as parameter to get the message that we defined when we threw the exception
     * @param request
     * It always required.
     * @return
     * The message of the given exception that we gave at Service. Returned as ResponseEntity(ex. ProjectService class)
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException (ProjectIdException ex, WebRequest request){
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectNotFoundException (ProjectNotFoundException ex, WebRequest request){
        ProjectNotFoundExceptionResponse exceptionResponse = new ProjectNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
