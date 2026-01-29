package com.serafim.core_store.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUpdateCategoryDTO(
        @NotBlank(message = "name is required.")
        String name
) {
}
