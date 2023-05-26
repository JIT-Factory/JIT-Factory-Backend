package com.jit.backend.service;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.entity.Material;
import com.jit.backend.entity.Product;
import com.jit.backend.entity.Sales;
import com.jit.backend.repository.MaterialRepository;
import com.jit.backend.repository.ProductRepository;
import com.jit.backend.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final SalesRepository salesRepository;
    private final MaterialRepository materialRepository;

    public List<Product> allProducts()  {
        List<Product> products = productRepository.findAll();
        return products;
    }
    public List<Product> statusOfProduct(String status){
        List<Product> products = productRepository.findAllByStatus(status);
        return products;
    }
    public List<Product> nameOfFactoryName(String factoryName){
        List<Product> products = productRepository.findAllByFactoryName(factoryName);
        return products;
    }

    public List<Product> dateOfFactoryName(String factoryName, LocalDate createTime){
        List<Product> products = productRepository.findAllByFactoryNameAndCreateDate(factoryName, createTime);
        return products;
    }

    public Product addProduct(ProductDto productDto) {
        Product product = Product.addProduct(productDto);

        // Sales 엔티티를 조회합니다.
        Sales sales = salesRepository.findByFactoryNameAndDate(productDto.getFactoryName(), LocalDate.now());

        if (sales == null) {
            // Sales 엔티티가 없을 경우 새로 생성합니다.
            sales = new Sales();
            sales.setFactoryName(productDto.getFactoryName());
            sales.setDate(LocalDate.now());
            sales.setSales(0L);
            sales.setSuccess(0L);
            sales.setFail(0L);
            sales.setCount(0L);
        }
        // Sales 엔티티의 필드 값을 업데이트합니다.
        sales.setSales(sales.getSales() + productDto.getSales());
        if(productDto.getStatus().equals("success"))
            sales.setSuccess(sales.getSuccess() + 1);
        else
            sales.setFail(sales.getFail() + 1);
        sales.setCount(sales.getCount() + 1);

        // Sales 엔티티를 저장합니다.
        salesRepository.save(sales);

        return productRepository.save(product);
    }

    public Product initProduct(ProductDto productDto) {
        Product product = Product.addProduct(productDto);
        return productRepository.save(product);
    }
}
