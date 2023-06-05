package com.jit.backend.repository;

import com.jit.backend.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {
    Camera findByFactoryName(String factoryName);
}
