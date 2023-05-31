package com.gpmrks.dslistapi.Dto;

import jakarta.validation.constraints.PositiveOrZero;

public record OrderForm(Long listId, Long gameId, @PositiveOrZero(message = "Must be greater than zero!") int destinationIndex) {
}
