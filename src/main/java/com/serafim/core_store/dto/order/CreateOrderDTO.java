package com.serafim.core_store.dto.order;

import java.util.List;

public record CreateOrderDTO(
        List<CreateOrderItemsDTO> items
) {
}
