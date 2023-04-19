package com.jit.backend.controller;

import com.jit.backend.dto.MaterialDto;
import com.jit.backend.dto.OrdersDto;
import com.jit.backend.entity.Material;
import com.jit.backend.entity.Orders;
import com.jit.backend.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "재료(재고)관리 페이지", description = "재료(재고)관리를 관리하는 api 입니다.")
@RestController
@RequestMapping("/api/material")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @Operation(summary = "재료(재고) 조회", description = "현재 가지고 있는 재고의 현황을 조회합니다.")
    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity getAllMaterials() {
        List<Material> materials;
        try{
            materials = materialService.allMaterials();
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(materials, HttpStatus.OK);
    }

    @Operation(summary = "재료 추가", description = "Unity의 창고에 재료를 추가할 수 있는 주문 기능입니다.")
    @PostMapping("/new")
    public ResponseEntity<String> addMaterials(@RequestBody MaterialDto materialDto) {
        materialService.addMaterial(materialDto);
        return ResponseEntity.ok("Add or update material success");
    }
}
