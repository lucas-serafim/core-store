package com.serafim.core_store.repository;

import com.serafim.core_store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p LEFT JOIN p.category c " +
            "WHERE (:name = '' OR p.name like %:name%) " +
            "AND (:category = '' OR c.name like %:category%)")
    Page<Product> filterProducts(
            @Param("name") String name,
            @Param("category") String category,
            Pageable pageable
    );
}
