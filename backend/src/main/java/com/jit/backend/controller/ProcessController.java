package com.jit.backend.controller;

import com.jit.backend.dto.ProcessControlDto;
import com.jit.backend.dto.ProcessDto;
import com.jit.backend.entity.Process;
import com.jit.backend.service.ProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "04. 공정 관리 페이지", description = "제작 공정과 내부의 컨베이어 벨트 동작 관련 api 입니다.")
@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @Operation(summary = "공정 상태 조회", description = "공정, 벨트 등 공정의 상태를 조회합니다.")
    @GetMapping("/{processName}")
    public ResponseEntity<?> showProcessStatus(
            @Parameter(description = "파라미터는 생성했던 공정의 이름을 입력합니다<br>ex) FrontProcess")
            @PathVariable ("processName") String processName) {
        Process process;
        try{
            process = processService.showStatus(processName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(process, HttpStatus.OK);
    }

    @Operation(summary = "공정 상태 업데이트", description = "공정, 벨트 등 공정의 상태를 업데이트 합니다. <br>모든 Key, Value를 입력하여 변경해도 가능하며 변경할 부분만 입력하여 변경하는 것도 가능합니다.<br>모든 Key에 대해서 Value의 값이 <br>  - run일 경우 Unity의 공정 혹은 벨트를 시작합니다.<br>  - strop일 경우 Unity의 공정 혹은 벨트를 정지합니다.")
    @PostMapping("/update")
    public ResponseEntity<Void> updateProcess(@RequestBody @Valid ProcessDto processDto) {
        processService.updateProcess(processDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "공정 정지", description = "공정, 벨트 등 공정의 모든 장치를 정지 합니다.<br> 모든 Key에 대한 Value의 값을 전부 \"stop\"으로 변경합니다.")
    @PostMapping("/stop")
    public ResponseEntity<Void> stopProcess(@RequestBody @Valid ProcessControlDto processControlDto) {
        processService.stopProcess(processControlDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "공정 시작", description = "공정, 벨트 등 공정의 모든 장치를 정지 합니다. <br> 모든 Key에 대한 Value의 값을 전부 \"run\"으로 변경합니다.")
    @PostMapping("/start")
    public ResponseEntity<Void> startProcess(@RequestBody @Valid ProcessControlDto processControlDto) {
        processService.startProcess(processControlDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
