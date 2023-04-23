package com.jit.backend.service;


import com.jit.backend.dto.FactoryControlDto;
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
                    .conveyorBeltWheel(factoryDto.getConveyorBeltWheel())
                    .conveyorBeltDoor(factoryDto.getConveyorBeltDoor())
                    .firstProcessMachineConveyorBelt(factoryDto.getFirstProcessMachineConveyorBelt())
                    .secondProcessMachineConveyorBelt(factoryDto.getSecondProcessMachineConveyorBelt())
                    .thirdProcessMachineConveyorBelt(factoryDto.getThirdProcessMachineConveyorBelt())
                    .fourthProcessMachineConveyorBelt(factoryDto.getFourthProcessMachineConveyorBelt())
                    .build();
        }else{
            if(factoryDto.getFactoryStatus() != null){
                factory.setFactoryStatus(factoryDto.getFactoryStatus());
            }
            if(factoryDto.getConveyorBeltWheel() != null){
                factory.setConveyorBeltWheel(factoryDto.getConveyorBeltWheel());
            }
            if(factoryDto.getConveyorBeltDoor() != null){
                factory.setConveyorBeltDoor(factoryDto.getConveyorBeltDoor());
            }
            if(factoryDto.getFirstProcessMachineConveyorBelt() != null){
                factory.setFirstProcessMachineConveyorBelt(factoryDto.getFirstProcessMachineConveyorBelt());
            }
            if(factoryDto.getSecondProcessMachineConveyorBelt() != null){
                factory.setSecondProcessMachineConveyorBelt(factoryDto.getSecondProcessMachineConveyorBelt());
            }
            if(factoryDto.getThirdProcessMachineConveyorBelt() != null){
                factory.setThirdProcessMachineConveyorBelt(factoryDto.getThirdProcessMachineConveyorBelt());
            }
            if(factoryDto.getFourthProcessMachineConveyorBelt() != null){
                factory.setFourthProcessMachineConveyorBelt(factoryDto.getFourthProcessMachineConveyorBelt());
            }
        }
        factoryRepository.saveAndFlush(factory);
    }

    public void stopFactory(FactoryControlDto factoryControlDto) {
        Factory factory = factoryRepository.findByFactoryName(factoryControlDto.getFactoryName());
        if(factory != null){
            factory.status("stop");
        }
        factoryRepository.saveAndFlush(factory);
    }

    public void startFactory(FactoryControlDto factoryControlDto) {
        Factory factory = factoryRepository.findByFactoryName(factoryControlDto.getFactoryName());
        if(factory != null){
            factory.status("start");
        }
        factoryRepository.saveAndFlush(factory);
    }
}
