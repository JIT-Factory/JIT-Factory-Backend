package com.jit.backend.repository;

import com.jit.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStatus(String status);
    List<Product> findAllByFactoryName(String factoryName);

    @Query("SELECT p FROM Product p WHERE p.factoryName = :factoryName AND DATE(p.createTime) = :createTime")
    List<Product> findAllByFactoryNameAndCreateDate(String factoryName, LocalDate createTime);
}