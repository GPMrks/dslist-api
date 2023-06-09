package com.gpmrks.dslistapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListNotFoundException extends RuntimeException{
    public ListNotFoundException(Long listId) {
        super("List not found with ID " + listId);
    }
}
