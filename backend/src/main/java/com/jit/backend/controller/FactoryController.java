package com.jit.backend.controller;

import com.jit.backend.dto.FactoryDto;
import com.jit.backend.entity.Factory;
import com.jit.backend.service.FactoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "공장 관리 페이지", description = "공장과 내부의 컨베이어 벨트 동작 관련 api 입니다.")
@RestController
@RequestMapping("/api/factory")
@RequiredArgsConstructor
public class FactoryController {

    private final FactoryService factoryService;

    @Operation(summary = "공장 상태 조회", description = "공장, 벨트 등 공장의 상태를 조회합니다.")
    @GetMapping("/{factoryName}")
    public ResponseEntity<?> showFactoryStatus(@PathVariable ("factoryName") String factoryName) {
        Factory factory;
        try{
            factory = factoryService.showStatus(factoryName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(factory, HttpStatus.OK);
    }

    @Operation(summary = "공장 상태 업데이트", description = "공장, 벨트 등 공장의 상태를 업데이트 합니다.")
    @PostMapping("/update")
    public ResponseEntity<Void> updateFactory(@RequestBody @Valid FactoryDto factoryDto) {
        factoryService.updateFactory(factoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "공장 정지", description = "공장, 벨트 등 공장의 모든 장치를 정지 합니다.")
    @PostMapping("/stop")
    public ResponseEntity<Void> stopFactory(@RequestBody @Valid FactoryDto factoryDto) {
        factoryService.stopFactory(factoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "공장 시작", description = "공장, 벨트 등 공장의 모든 장치를 정지 합니다.")
    @PostMapping("/start")
    public ResponseEntity<Void> startFactory(@RequestBody @Valid FactoryDto factoryDto) {
        factoryService.startFactory(factoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
