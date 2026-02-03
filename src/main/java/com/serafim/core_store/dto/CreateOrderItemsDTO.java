package com.serafim.core_store.dto;

import java.util.UUID;

public record CreateOrderItemsDTO(
        UUID id,
        Integer quantity
) {
}
