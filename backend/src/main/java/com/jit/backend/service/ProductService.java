package com.jit.backend.service;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.entity.Product;
import com.jit.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
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

    public Integer sumSales(){
        return productRepository.sumSales();
    }

    @Transactional
    public void addProduct(ProductDto productDto) {
        Product product = Product.addProduct(productDto);
        productRepository.save(product);
    }

    public Map<String, Long> getMonthlySales() {
        return productRepository.sumSalesMonthly();
    }

    public Map<String, Long> getWeeklySales() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);
        return productRepository.sumSalesWeekly(startDate, endDate);
    }

    public Map<String, Long> getDailySales() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.with(LocalTime.MIN);
        return productRepository.sumSalesDaily(startDate, endDate);
    }


}
