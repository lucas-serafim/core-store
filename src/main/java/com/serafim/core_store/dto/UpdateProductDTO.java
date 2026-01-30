package com.serafim.core_store.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record UpdateProductDTO(

        Optional<@Size(min = 3, max = 150) String> name,

        Optional<@Min(1) Integer> quantity,

        Optional<@Min(1) Integer> price,

        Optional<String> description
) {
}
