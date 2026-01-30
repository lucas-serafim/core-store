package com.serafim.core_store.service;

import com.serafim.core_store.dto.CategoryDTO;
import com.serafim.core_store.dto.CreateUpdateCategoryDTO;
import com.serafim.core_store.exception.CategoryAlreadyExistsException;
import com.serafim.core_store.exception.CategoryNotFoundException;
import com.serafim.core_store.model.Category;
import com.serafim.core_store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional
    public CategoryDTO create(CreateUpdateCategoryDTO dto) {
        String categoryNameLower = dto.name().toLowerCase();

        boolean isCategoryExist = this.repository.existsByName(categoryNameLower);

        if (isCategoryExist) {
            throw new CategoryAlreadyExistsException();
        }

        Category category = new Category(categoryNameLower);
        this.repository.save(category);

        return new CategoryDTO(category.getId(), category.getName());
    }

    @Transactional
    public CategoryDTO updateName(UUID id, String name) {
        String categoryNameLower = name.toLowerCase();

        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        category.updateName(categoryNameLower);

        this.repository.save(category);

        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }
}
