package com.gpmrks.dslistapi.Dto;

import jakarta.validation.constraints.NotBlank;

public record ListForm(@NotBlank(message = "List must have a name!") String name) {
}
