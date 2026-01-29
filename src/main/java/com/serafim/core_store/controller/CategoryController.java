package com.serafim.core_store.controller;

import com.serafim.core_store.dto.CategoryDTO;
import com.serafim.core_store.dto.CreateUpdateCategoryDTO;
import com.serafim.core_store.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/core-store/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping()
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CreateUpdateCategoryDTO dto) {
        CategoryDTO categoryDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateName(
            @PathVariable UUID id,
            @Valid @RequestBody CreateUpdateCategoryDTO dto
    ) {
        CategoryDTO categoryDTO = service.updateName(id, dto.name());
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }
}
