package com.jit.backend.service;

import com.jit.backend.entity.Product;
import com.jit.backend.entity.Sales;
import com.jit.backend.repository.ProductRepository;
import com.jit.backend.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SalesService {

    private final SalesRepository salesRepository;

    public List<Sales> allSales()  {
        List<Sales> sales = salesRepository.findAll();
        return sales;
    }

    public List<Sales> nameOfFactoryName(String factoryName){
        List<Sales> salesList = salesRepository.findAllByFactoryName(factoryName);
        return salesList;
    }

    public List<Sales> getSalesByMonth(String factoryName, int year, int month) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

        return salesRepository.findByFactoryNameAndDateBetween(factoryName, startOfMonth, endOfMonth);
    }
    public List<Sales> getSalesForPastMonth(String factoryName) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);

        return salesRepository.findByFactoryNameAndDateBetween(factoryName, startDate, endDate);
    }


    public List<Sales> getSalesForPastWeek(String factoryName) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusWeeks(1);

        return salesRepository.findByFactoryNameAndDateBetween(factoryName, startDate, endDate);
    }

    public List<Sales> getSalesByDay(String factoryName, LocalDate date) {
        return salesRepository.findByFactoryNameAndDateBetween(factoryName, date, date);
    }


}
