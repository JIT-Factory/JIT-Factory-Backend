package com.jit.backend.service;

import com.jit.backend.dto.CameraDto;
import com.jit.backend.entity.Camera;
import com.jit.backend.repository.CameraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CameraService {
    private final CameraRepository cameraRepository;

    public void changeCameraNumber(CameraDto cameraDto) {
        Camera factoryCamera = cameraRepository.findByFactoryName(cameraDto.getFactoryName());
        if (factoryCamera != null) {
            Long newCameraNumber = cameraDto.getCameraNumber();
            factoryCamera.setCameraNumber(newCameraNumber);
            cameraRepository.save(factoryCamera);
        } else {
            String factoryName = cameraDto.getFactoryName();
            Long cameraNumber = cameraDto.getCameraNumber();
            Camera newCamera = Camera.builder()
                    .factoryName(factoryName)
                    .cameraNumber(cameraNumber)
                    .build();
            cameraRepository.save(newCamera);
        }
    }

    public Camera showCameraInfo(String factoryName){
        Camera camera = cameraRepository.findByFactoryName(factoryName);
        return camera;
    }
}
