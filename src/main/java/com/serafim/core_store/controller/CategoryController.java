package com.serafim.core_store.controller;

import com.serafim.core_store.dto.CategoryDTO;
import com.serafim.core_store.dto.CreateCategoryDTO;
import com.serafim.core_store.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/core-store/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping()
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CreateCategoryDTO dto) {
        CategoryDTO categoryDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }
}
