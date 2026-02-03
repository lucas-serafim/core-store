package com.serafim.core_store.dto;

import com.serafim.core_store.model.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        Integer totalPrice,
        OrderStatusEnum status,
        List<OrderItemsDTO> orderItems
) {
}
