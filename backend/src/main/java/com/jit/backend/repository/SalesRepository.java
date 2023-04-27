package com.jit.backend.repository;

import com.jit.backend.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    Sales findByFactoryNameAndDate(String factoryName, LocalDate date);

    List<Sales> findAllByFactoryName(String factoryName);

    List<Sales> findByFactoryNameAndDateBetween(String factoryName, LocalDate year, LocalDate month);


}
