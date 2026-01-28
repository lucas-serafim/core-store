package com.serafim.core_store.service;

import com.serafim.core_store.dto.CategoryDTO;
import com.serafim.core_store.dto.CreateCategoryDTO;
import com.serafim.core_store.exception.CategoryAlreadyExistsException;
import com.serafim.core_store.model.Category;
import com.serafim.core_store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDTO create(CreateCategoryDTO dto) {

        String categoryNameLower = dto.name().toLowerCase();

        boolean isCategoryExist = this.repository.existsByName(categoryNameLower);

        if (isCategoryExist) {
            throw new CategoryAlreadyExistsException();
        }

        Category category = new Category(categoryNameLower);
        this.repository.save(category);

        return new CategoryDTO(category.getId(), category.getName());
    }
}
