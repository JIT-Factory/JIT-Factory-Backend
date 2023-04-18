package com.jit.backend.service;


import com.jit.backend.dto.FactoryDto;
import com.jit.backend.entity.Factory;
import com.jit.backend.repository.FactoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FactoryService {

    private final FactoryRepository factoryRepository;

    public Factory showStatus(String factoryName){
        Factory factory= factoryRepository.findByFactoryName(factoryName );
        return factory;
    }

    public void updateFactory(FactoryDto factoryDto) {
        Factory factory = factoryRepository.findByFactoryName(factoryDto.getFactoryName());
        if(factory == null){
            factory = Factory.builder()
                    .factoryName(factoryDto.getFactoryName())
                    .factoryStatus(factoryDto.getFactoryStatus())
                    .insultBelt(factoryDto.getInsultBelt())
                    .productionBelt(factoryDto.getProductionBelt())
                    .inspectionProductABelt(factoryDto.getInspectionProductABelt())
                    .inspectionProductBBelt(factoryDto.getInspectionProductBBelt())
                    .inspectionProductCBelt(factoryDto.getInspectionProductCBelt())
                    .build();
        }else{
            if(factoryDto.getFactoryStatus() != null){
                factory.setFactoryStatus(factoryDto.getFactoryStatus());
            }
            if(factoryDto.getInsultBelt() != null){
                factory.setInsultBelt(factoryDto.getInsultBelt());
            }
            if(factoryDto.getProductionBelt() != null){
                factory.setProductionBelt(factoryDto.getProductionBelt());
            }
            if(factoryDto.getInspectionProductABelt() != null){
                factory.setInspectionProductABelt(factoryDto.getInspectionProductABelt());
            }
            if(factoryDto.getInspectionProductBBelt() != null){
                factory.setInspectionProductBBelt(factoryDto.getInspectionProductBBelt());
            }
            if(factoryDto.getInspectionProductCBelt() != null){
                factory.setInspectionProductCBelt(factoryDto.getInspectionProductCBelt());
            }
        }
        factoryRepository.saveAndFlush(factory);
    }

    public void stopFactory(FactoryDto factoryDto) {
        Factory factory = factoryRepository.findByFactoryName(factoryDto.getFactoryName());
        if(factory != null){
            factory.status("stop");
        }
        factoryRepository.saveAndFlush(factory);
    }

    public void startFactory(FactoryDto factoryDto) {
        Factory factory = factoryRepository.findByFactoryName(factoryDto.getFactoryName());
        if(factory != null){
            factory.status("start");
        }
        factoryRepository.saveAndFlush(factory);
    }
}
