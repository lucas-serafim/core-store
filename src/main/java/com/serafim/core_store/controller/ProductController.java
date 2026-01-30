package com.serafim.core_store.controller;

import com.serafim.core_store.dto.CreateProductDTO;
import com.serafim.core_store.dto.ProductDTO;
import com.serafim.core_store.dto.UpdateProductDTO;
import com.serafim.core_store.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/core-store/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/categories/{categoryId}")
    public ResponseEntity<ProductDTO> create(
            @PathVariable UUID categoryId,
            @Valid @RequestBody CreateProductDTO dto
    ) {
        ProductDTO productDTO = this.service.create(categoryId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDTO> update(
            @PathVariable UUID productId,
            @Valid @RequestBody UpdateProductDTO dto
    ) {
        ProductDTO productDTO = this.service.update(productId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }
}
