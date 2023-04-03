package com.jit.backend.controller;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.entity.Product;
import com.jit.backend.jwt.AuthDto;
import com.jit.backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

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

    @GetMapping("/status/{status}")
    ResponseEntity statusOfProduct(@PathVariable ("status") String status) {
        List<Product> productList;
        try{
            productList = productService.statusOfProduct(status);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(productList, HttpStatus.OK);
    }

    @GetMapping("/name/{productName}")
    ResponseEntity nameOfProduct(@PathVariable ("productName") String productName) {
        List<Product> productList;
        try{
            productList = productService.nameOfProduct(productName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(productList, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
