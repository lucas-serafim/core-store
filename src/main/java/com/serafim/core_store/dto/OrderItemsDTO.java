package com.serafim.core_store.dto;

import java.util.UUID;

public record OrderItemsDTO(
        UUID id,
        Integer quantity,
        Integer priceAtPurchase
) {
}
