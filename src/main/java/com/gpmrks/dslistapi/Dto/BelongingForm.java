package com.gpmrks.dslistapi.Dto;

import jakarta.validation.constraints.PositiveOrZero;

public record BelongingForm (@PositiveOrZero(message = "Must be greater than zero and be a valid Game ID") Long gameId,
                             @PositiveOrZero(message = "Must be greater than zero and be a valid List ID") Long listId){

}
