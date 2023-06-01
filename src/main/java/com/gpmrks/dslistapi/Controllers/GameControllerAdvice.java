package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Exceptions.GameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GameControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    public ProblemDetail listNotFoundException(GameNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/games/not-found"));
        problemDetail.setTitle("Game not found!");
        problemDetail.setDetail("Must be a valid Game ID!");
        problemDetail.setProperty("message: ", ex.getMessage());
        problemDetail.setProperty("category", "Games");
        problemDetail.setProperty("timeStamp", Instant.now());
        return problemDetail;
    }

}
