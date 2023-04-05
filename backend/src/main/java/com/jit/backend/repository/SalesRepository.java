package com.jit.backend.repository;

import com.jit.backend.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query("SELECT COUNT(DISTINCT p.id) AS totalProductCount, SUM(p.sales) AS salesSum, "
            + "SUM(CASE WHEN p.status = 'success' THEN 1 ELSE 0 END) AS success, "
            + "SUM(CASE WHEN p.status = 'fail' THEN 1 ELSE 0 END) AS fail "
            + "FROM Product p")
    Map<String, Long> countByStatus();

    // 월간
    @Query("SELECT COUNT(DISTINCT p.id) AS productCount, SUM(p.sales) AS salesSum, "
            + "SUM(CASE WHEN p.status = 'success' THEN 1 ELSE 0 END) AS success, "
            + "SUM(CASE WHEN p.status = 'fail' THEN 1 ELSE 0 END) AS fail "
            + "FROM Product p "
            + "WHERE YEAR(p.createTime) = YEAR(CURRENT_TIMESTAMP) "
            + "AND MONTH(p.createTime) = MONTH(CURRENT_TIMESTAMP)")
    Map<String, Long> sumSalesMonthly();

    // 주간
    @Query("SELECT COUNT(DISTINCT p.id) AS productCount, SUM(p.sales) AS salesSum, "
            + "SUM(CASE WHEN p.status = 'success' THEN 1 ELSE 0 END) AS success, "
            + "SUM(CASE WHEN p.status = 'fail' THEN 1 ELSE 0 END) AS fail "
            + "FROM Product p "
            + "WHERE p.createTime BETWEEN :startDate AND :endDate")
    Map<String, Long> sumSalesWeekly(@Param("startDate") LocalDateTime startDate,
                                     @Param("endDate") LocalDateTime endDate);

    // 당일
    @Query("SELECT COUNT(DISTINCT p.id) AS productCount, SUM(p.sales) AS salesSum, "
            + "SUM(CASE WHEN p.status = 'success' THEN 1 ELSE 0 END) AS success, "
            + "SUM(CASE WHEN p.status = 'fail' THEN 1 ELSE 0 END) AS fail "
            + "FROM Product p "
            + "WHERE p.createTime BETWEEN :startDate AND :endDate")
    Map<String, Long> sumSalesDaily(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);
    Sales findByDate(LocalDate date);
}
