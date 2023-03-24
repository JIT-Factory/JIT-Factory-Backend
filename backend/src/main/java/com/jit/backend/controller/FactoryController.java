package com.jit.backend.controller;

import com.jit.backend.dto.FactoryDto;
import com.jit.backend.entity.Factory;
import com.jit.backend.service.FactoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class FactoryController {

    private final FactoryService factoryService;
    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity dashboard() {
        List<Factory> factoryLogs;
        try{
            factoryLogs = factoryService.allLogs();
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(factoryLogs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addLogs(@RequestBody @Valid FactoryDto factoryDto) {
        factoryService.addLogs(factoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
