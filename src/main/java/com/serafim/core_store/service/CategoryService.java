package com.serafim.core_store.service;

import com.serafim.core_store.dto.CategoryDTO;
import com.serafim.core_store.dto.CreateUpdateCategoryDTO;
import com.serafim.core_store.exception.CategoryAlreadyExistsException;
import com.serafim.core_store.exception.CategoryNotFoundException;
import com.serafim.core_store.model.Category;
import com.serafim.core_store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

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

    public CategoryDTO updateName(UUID id, String name) {
        String categoryNameLower = name.toLowerCase();

        Optional<Category> category = this.repository.findById(id);

        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category not found. ID: " + id);
        }

        Category categoryFounded = category.get();

        categoryFounded.updateName(categoryNameLower);

        this.repository.save(categoryFounded);

        return new CategoryDTO(
                categoryFounded.getId(),
                categoryFounded.getName()
        );
    }
}
