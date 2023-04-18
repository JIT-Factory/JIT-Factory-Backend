package com.jit.backend.repository;

import com.jit.backend.dto.MaterialDto;
import com.jit.backend.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    Material findByMaterialName(String materialName);
}
