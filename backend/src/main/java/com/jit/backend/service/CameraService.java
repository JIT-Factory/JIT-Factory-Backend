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

    public void changeCameraNumber(CameraDto cameraDto){
        Optional<Camera> optionalCamera = cameraRepository.findById(1L);
        if (optionalCamera.isPresent()) {
            Camera camera = optionalCamera.get();
            Long newCameraNumber = cameraDto.getCameraNumber();
            camera.setCameraNumber(newCameraNumber);
            cameraRepository.save(camera);
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
    public List<Camera> showCameraInfo(){
        List<Camera> camera = cameraRepository.findAll();
        return camera;
    }
}
