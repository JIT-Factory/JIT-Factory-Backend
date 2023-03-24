package com.jit.backend.service;

import com.jit.backend.dto.FactoryDto;
import com.jit.backend.entity.Factory;
import com.jit.backend.repository.FactoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FactoryService {

    private final FactoryRepository factoryRepository;
    public List<Factory> allLogs()  {
        List<Factory> factoryLogs = factoryRepository.findAll();
        return factoryLogs;
    }

    @Transactional
    public void addLogs(FactoryDto factoryDto) {
        Factory factory = Factory.addLogs(factoryDto);
        factoryRepository.save(factory);
    }
}
