package com.serafim.core_store.dto;

import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        Integer quantity,
        Integer price,
        String description,
        String categoryName
) {
}
