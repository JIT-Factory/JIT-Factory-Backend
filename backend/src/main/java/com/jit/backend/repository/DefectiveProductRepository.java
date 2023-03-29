package com.jit.backend.repository;

import com.jit.backend.entity.DefectiveProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectiveProductRepository extends JpaRepository<DefectiveProduct, Long> {
}
