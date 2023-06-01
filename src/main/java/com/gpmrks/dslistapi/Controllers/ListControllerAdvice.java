package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Exceptions.ListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class ListControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ListNotFoundException.class)
    public ProblemDetail listNotFoundException(ListNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/lists/not-found"));
        problemDetail.setTitle("List not found!");
        problemDetail.setDetail("Must be a valid List ID!");
        problemDetail.setProperty("message: ", ex.getMessage());
        problemDetail.setProperty("category", "Lists");
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ProblemDetail IndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/lists/index-out-of-bounds"));
        problemDetail.setTitle("Index out of Bounds!");
        problemDetail.setDetail("Must be a valid index of the list!");
        problemDetail.setProperty("message: ", ex.getMessage());
        problemDetail.setProperty("category", "Lists");
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }
}
