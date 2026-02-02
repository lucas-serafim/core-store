package com.serafim.core_store.service;

import com.serafim.core_store.dto.CreateProductDTO;
import com.serafim.core_store.dto.ProductDTO;
import com.serafim.core_store.dto.UpdateProductDTO;
import com.serafim.core_store.exception.CategoryNotFoundException;
import com.serafim.core_store.exception.ProductNotFoundException;
import com.serafim.core_store.model.Category;
import com.serafim.core_store.model.Product;
import com.serafim.core_store.repository.CategoryRepository;
import com.serafim.core_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public ProductDTO create(UUID categoryId, CreateProductDTO dto) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);

        Product product = new Product(
                dto.name(),
                dto.quantity(),
                dto.price(),
                dto.description(),
                category
        );

        productRepository.save(product);

        return mapToDTO(product);
    }

    @Transactional
    public ProductDTO update(UUID productId, UpdateProductDTO dto) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        dto.name().ifPresent(product::updateName);
        dto.quantity().ifPresent(product::updateQuantity);
        dto.price().ifPresent(product::updatePrice);
        dto.description().ifPresent(product::updateDescription);

        return mapToDTO(product);
    }

    @Transactional
    public void delete(UUID productId) {
        productRepository.deleteById(productId);
    }

    public ProductDTO findById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return mapToDTO(product);
    }

    public List<ProductDTO> findAll(int pageNumber, int pageSize, String name, String category) {

        name = (name != null) ? name : "";
        category = (category != null) ? category : "";

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> products = productRepository.filterProducts(name, category, pageable);

        return products.map(this::mapToDTO).stream().toList();
    }

    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory().getName()
        );
    }
}
