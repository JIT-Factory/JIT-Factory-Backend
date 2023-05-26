package com.jit.backend.repository;

import com.jit.backend.entity.Material;
import com.jit.backend.entity.Orders;
import com.jit.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findByFactoryNameAndMaterialName(String FactoryName, String materialName);
    List<Material> findAllByFactoryName(String factoryName);
}
