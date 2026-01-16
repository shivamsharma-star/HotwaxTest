package com.softmax.testhotwax.repository;

import com.softmax.testhotwax.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
