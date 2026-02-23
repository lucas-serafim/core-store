package com.serafim.core_store.dto.order;

import java.util.UUID;

public record CreateOrderItemsDTO(
        UUID id,
        Integer quantity
) {
}
