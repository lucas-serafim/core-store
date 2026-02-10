package com.serafim.core_store.controller;

import com.serafim.core_store.dto.CreateOrderDTO;
import com.serafim.core_store.dto.OrderDTO;
import com.serafim.core_store.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/core-store/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<OrderDTO> create(
            @Valid @RequestBody CreateOrderDTO dto
    ) {
        OrderDTO orderDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @PostMapping("/{orderId}/items/{orderItemId}/remove")
    public ResponseEntity<OrderDTO> removeOrderItem(
            @PathVariable() UUID orderId,
            @PathVariable() UUID orderItemId
    ) {
        OrderDTO orderDTO = service.removeItem(orderId, orderItemId);
        return ResponseEntity.ok(orderDTO);
    }


    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDTO> cancel(
            @PathVariable() UUID orderId
    ) {
        OrderDTO orderDTO = service.cancel(orderId);
        return ResponseEntity.ok(orderDTO);
    }
}
