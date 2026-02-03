package com.serafim.core_store.dto;

import java.util.List;

public record CreateOrderDTO(
        List<CreateOrderItemsDTO> items
) {
}
