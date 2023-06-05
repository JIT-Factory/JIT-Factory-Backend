package com.jit.backend.controller;

import com.jit.backend.dto.CameraDto;
import com.jit.backend.entity.Camera;
import com.jit.backend.entity.Product;
import com.jit.backend.service.CameraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "09. 카메라 조절 페이지", description = "카메라 조절하는 api 입니다.")
@RequestMapping("/api/camera")
public class CameraController {

    public final CameraService cameraService;

    @Operation(summary = "카메라 위치 업데이트", description = "FactoryName과 CameraNumber에 해당하는 위치의 카메라로 화면을 전환합니다.")
    @PostMapping("/change")
    public ResponseEntity<String> changeCameraNumber(@RequestBody CameraDto cameraDto) {
        cameraService.changeCameraNumber(cameraDto);
        return ResponseEntity.ok("factoryName : " + cameraDto.getFactoryName() + " cameraNumber : " + cameraDto.getCameraNumber());
    }


    @GetMapping("/show/{factoryName}")
    @Operation(summary = "카메라 조회", description = "파라미터에 해당하는 FactoryName의 CameraNumber에 해당하는 카메라 위치를 조회합니다.")
    public ResponseEntity showCameraInfo(@PathVariable String factoryName){
        Camera camera;
        try{
            camera = cameraService.showCameraInfo(factoryName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(camera, HttpStatus.OK);
    }

}
