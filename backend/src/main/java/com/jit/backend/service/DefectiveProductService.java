package com.jit.backend.service;

import com.jit.backend.dto.DefectiveProductDto;
import com.jit.backend.entity.DefectiveProduct;
import com.jit.backend.repository.DefectiveProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefectiveProductService {

    private final DefectiveProductRepository defectiveProductRepository;
    public List<DefectiveProduct> allLogs()  {
        List<DefectiveProduct> defectiveProductLogs = defectiveProductRepository.findAll();
        return defectiveProductLogs;
    }

    @Transactional
    public void addLogs(DefectiveProductDto defectiveProductDto) {
        DefectiveProduct defectiveProduct = DefectiveProduct.addLogs(defectiveProductDto);
        defectiveProductRepository.save(defectiveProduct);
    }
}
