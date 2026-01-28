package com.serafim.core_store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(
        @NotBlank(message = "name is required.")
        String name
) {
}
