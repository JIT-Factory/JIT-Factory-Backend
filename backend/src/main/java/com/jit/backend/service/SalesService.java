package com.jit.backend.service;

import com.jit.backend.dto.SalesDto;
import com.jit.backend.entity.Product;
import com.jit.backend.entity.Sales;
import com.jit.backend.repository.ProductRepository;
import com.jit.backend.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SalesService {

    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;

    public Map<String, Long> getMonthlySales() {
        return salesRepository.sumSalesMonthly();
    }
    public Map<String, Long> getWeeklySales() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);
        return salesRepository.sumSalesWeekly(startDate, endDate);
    }
    public Map<String, Long> getDailySales() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.with(LocalTime.MIN);
        return salesRepository.sumSalesDaily(startDate, endDate);
    }

    public Map<String, Long> sumSales(){
        return salesRepository.countByStatus();
    }

    public List<Sales> allSales()  {
        List<Sales> sales = salesRepository.findAll();
        return sales;
    }

    public List<Sales> getSalesByDate() {
        List<Sales> salesList = new ArrayList<>();
        Map<LocalDate, Sales> salesMap = new HashMap<>();

        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            LocalDateTime createTime = product.getCreateTime();
            LocalDate date = createTime.toLocalDate();
            Sales sales = salesMap.get(date);
            if (sales == null) {
                sales = new Sales();
                sales.setDate(LocalDate.from(createTime));
                sales.setSales(0L);
                sales.setSuccess(0L);
                sales.setFail(0L);
                sales.setCount(0L);
                salesMap.put(date, sales);
            }

            long salesAmount = product.getSales();
            sales.setSales(sales.getSales() + salesAmount);

            String status = product.getStatus();
            if ("success".equals(status)) {
                sales.setSuccess(sales.getSuccess() + 1);
            } else if ("fail".equals(status)) {
                sales.setFail(sales.getFail() + 1);
            }

            sales.setCount(sales.getCount() + 1);
        }

        int id = 0;
        for (Map.Entry<LocalDate, Sales> entry : salesMap.entrySet()) {
            Sales sales = entry.getValue();
            sales.setId((long) ++id);
            salesList.add(sales);
            salesRepository.save(sales);
        }
        return salesList;
    }

    public Sales salesByDate(LocalDate date) {
        Sales sales = salesRepository.findByDate(date);
        return sales;
    }
}
