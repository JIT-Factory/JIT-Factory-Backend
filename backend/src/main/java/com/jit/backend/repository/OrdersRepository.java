package com.jit.backend.repository;

import com.jit.backend.entity.Orders;
import com.jit.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByProductName(String productName);

    List<Orders> findAllByFactoryName(String factoryName);

    Orders findByFactoryName(String factoryName);

    Orders findByFactoryNameAndProductName(String FactoryName, String ProductName);

}
