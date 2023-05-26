package com.jit.backend.service;

import com.jit.backend.dto.MaterialDto;
import com.jit.backend.dto.OrdersDto;
import com.jit.backend.entity.Material;
import com.jit.backend.entity.Orders;
import com.jit.backend.entity.Product;
import com.jit.backend.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    public List<Material> allMaterials()  {
        List<Material> materials = materialRepository.findAll();
        return materials;
    }

    public void addOrUpdateMaterial(MaterialDto materialDto) {
        Material material = materialRepository.findByFactoryNameAndMaterialName(materialDto.getFactoryName(), materialDto.getMaterialName());

        if (material == null) {
            material = Material.builder()
                    .materialName(materialDto.getMaterialName())
                    .factoryName(materialDto.getFactoryName())
                    .stock(materialDto.getStock())
                    .build();
        } else {
            material.updateMaterial(materialDto);
        }
        materialRepository.saveAndFlush(material);
    }

    public List<Material> nameOfFactoryName(String factoryName){
        List<Material> materialList = materialRepository.findAllByFactoryName(factoryName);
        return materialList;
    }
}
