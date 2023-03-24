package com.jit.backend.service;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.entity.Product;
import com.jit.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> allProducts()  {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Transactional
    public void addProduct(ProductDto productDto) {
        Product product = Product.addProduct(productDto);
        productRepository.save(product);
    }
}
