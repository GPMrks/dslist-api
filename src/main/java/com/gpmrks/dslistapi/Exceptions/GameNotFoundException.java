package com.gpmrks.dslistapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(Long gameId) {
        super("Game not found with ID " + gameId);
    }
}
