package com.jit.backend.controller;

import com.jit.backend.dto.MaterialDto;
import com.jit.backend.dto.OrdersDto;
import com.jit.backend.entity.Material;
import com.jit.backend.entity.Orders;
import com.jit.backend.entity.Product;
import com.jit.backend.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "05. 재료(재고)관리 페이지", description = "재료(재고)관리를 관리하는 api 입니다.")
@RestController
@RequestMapping("/api/material")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @Operation(summary = "재료(재고) 조회", description = "현재 가지고 있는 재고의 현황을 조회합니다. " +
            "<br>Product가 추가될때 stock이 감소하는 것을 볼 수 있습니다.")
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

    @Operation(summary = "공장 이름에 대한 상품 조회", description = "Factory Name에 해당하는 상품의 재료들의 내역을 조회합니다.")
    @GetMapping("/name/{factoryName}")
    public ResponseEntity nameOfProduct(
            @Parameter(description = "파라미터는 factoryName의 값을 입력합니다. <br>ex) CarFactory")
            @PathVariable ("factoryName") String factoryName) {
        List<Material> materialList;
        try{
            materialList = materialService.nameOfFactoryName(factoryName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(materialList, HttpStatus.OK);
    }

    @Operation(summary = "재료 추가", description = "Unity의 창고에 재료를 추가할 수 있는 주문 기능입니다. " +
            "<br>\"factoryName\"과 \"materialName\"의 Value에 해당하는 이름을 가진 테이블의 stock이 \"stock\"의 Value만큼 증가합니다.")
    @PostMapping("/new")
    public ResponseEntity<String> addMaterials(@RequestBody MaterialDto materialDto) {
        materialService.addOrUpdateMaterial(materialDto);
        return ResponseEntity.ok("Add or update material success");
    }
}
