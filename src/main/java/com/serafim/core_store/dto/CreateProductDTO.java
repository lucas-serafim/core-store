package com.serafim.core_store.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProductDTO(

        @NotBlank(message = "name is required")
        @Size(min = 3, max = 150)
        String name,

        @NotNull(message = "quantity should be greater than 0")
        @Min(1)
        Integer quantity,

        @NotNull(message = "price should be greater than 0")
        @Min(1)
        Integer price,

        String description
) {
}
