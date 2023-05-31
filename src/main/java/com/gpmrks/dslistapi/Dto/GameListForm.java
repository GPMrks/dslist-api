package com.gpmrks.dslistapi.Dto;

import jakarta.validation.constraints.NotBlank;

public record GameListForm (@NotBlank(message = "List must have a name!") String name) {
}
