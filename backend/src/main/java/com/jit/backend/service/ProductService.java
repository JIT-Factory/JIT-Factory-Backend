package com.jit.backend.service;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.entity.Product;
import com.jit.backend.repository.ProductRepository;
import com.jit.backend.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final SalesRepository salesRepository;

    public List<Product> allProducts()  {
        List<Product> products = productRepository.findAll();
        return products;
    }
    public List<Product> statusOfProduct(String status){
        List<Product> products = productRepository.findAllByStatus(status);
        return products;
    }
    public List<Product> nameOfProduct(String productName){
        List<Product> products = productRepository.findAllByProductName(productName);
        return products;
    }

    public Product addProduct(ProductDto productDto) {
        Product product = Product.addProduct(productDto);
        return productRepository.save(product);
    }
}
