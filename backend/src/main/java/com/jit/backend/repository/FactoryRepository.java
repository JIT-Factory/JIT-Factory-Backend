package com.jit.backend.repository;

import com.jit.backend.entity.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FactoryRepository extends JpaRepository<Factory, Long> {
    Factory findByFactoryName(String factoryName);
}