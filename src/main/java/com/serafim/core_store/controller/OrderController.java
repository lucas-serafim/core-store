package com.serafim.core_store.controller;

import com.serafim.core_store.dto.CreateOrderDTO;
import com.serafim.core_store.dto.OrderDTO;
import com.serafim.core_store.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
