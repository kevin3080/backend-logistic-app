package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter;

import com.logisticapp.backend_logistic_app.domain.model.Product;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository.SpringDataProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl {
    private final SpringDataProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
