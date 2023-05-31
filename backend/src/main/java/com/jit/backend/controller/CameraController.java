package com.jit.backend.controller;

import com.jit.backend.dto.CameraDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "09. 카메라 조절 페이지", description = "카메라 조절하는 api 입니다.")
@RequestMapping("/api/camera")
public class CameraController {

    @Operation(summary = "카메라 위치 업데이트", description = "FactoryName과 CameraNumber에 해당하는 위치의 카메라로 화면을 전환합니다.")
    @PostMapping("/change")
    public ResponseEntity<String> changeCameraNumber(@RequestBody CameraDto cameraDto) {
        return ResponseEntity.ok(cameraDto.getFactoryName() + ", " + cameraDto.getCameraNumber());
    }
}
