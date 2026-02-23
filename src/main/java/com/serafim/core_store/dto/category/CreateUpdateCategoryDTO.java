package com.serafim.core_store.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CreateUpdateCategoryDTO(
        @NotBlank(message = "name is required.")
        String name
) {
}
