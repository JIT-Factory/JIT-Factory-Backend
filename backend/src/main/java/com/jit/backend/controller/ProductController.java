package com.jit.backend.controller;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.entity.Product;
import com.jit.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "07. 상품 페이지", description = "상품 관련 api 입니다.")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 조회", description = "생산된 모든 상품을 조회합니다.")
    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity dashboard() {
        List<Product> productList;
        try{
            productList = productService.allProducts();
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(productList, HttpStatus.OK);
    }

    @Operation(summary = "불량, 성공 상품조회", description = "Status에 따른 상품을 조회합니다.<br> Status는 success와 fail로 구성됩니다.")
    @GetMapping("/status/{status}")
    ResponseEntity statusOfProduct(
            @Parameter(description = "파라미터는 status의 값을 입력합니다. <br>ex) success")
            @PathVariable ("status") String status) {
        List<Product> productList;
        try{
            productList = productService.statusOfProduct(status);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(productList, HttpStatus.OK);
    }

    @Operation(summary = "공장 이름에 대한 상품 조회", description = "Factory Name에 해당하는 상품의 생산 내역을 조회합니다.")
    @GetMapping("/name/{factoryName}")
    public ResponseEntity nameOfProduct(
            @Parameter(description = "파라미터는 factoryName의 값을 입력합니다. <br>ex) CarFactory")
            @PathVariable ("factoryName") String factoryName) {
        List<Product> productList;
        try{
            productList = productService.nameOfFactoryName(factoryName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(productList, HttpStatus.OK);
    }


    @Operation(summary = "상품 추가", description = "상품을 추가합니다.<br>createTime은 현재 시간으로 자동 추가됩니다.<br>reason은 status가 fail인 경우에만 작성합니다.<br>sales는 status가 success인 경우에만 작성합니다. fail인 경우에는 비워두거나 0을 입력합니다.<br>materialName에 해당하는 requireMaterial만큼 Material의 Stock을 감소시킵니다.")
    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
