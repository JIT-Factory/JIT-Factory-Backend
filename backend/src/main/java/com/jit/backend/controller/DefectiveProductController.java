package com.jit.backend.controller;

import com.jit.backend.dto.DefectiveProductDto;
import com.jit.backend.entity.DefectiveProduct;
import com.jit.backend.service.DefectiveProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class DefectiveProductController {

    private final DefectiveProductService defectiveProductService;
    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity dashboard() {
        List<DefectiveProduct> defectiveProductLogs;
        try{
            defectiveProductLogs = defectiveProductService.allLogs();
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(defectiveProductLogs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addLogs(@RequestBody @Valid DefectiveProductDto defectiveProductDto) {
        defectiveProductService.addLogs(defectiveProductDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
