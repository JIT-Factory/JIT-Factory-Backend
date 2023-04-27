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

import java.util.List;


@Tag(name = "04. 공정 관리 페이지", description = "제작 공정과 내부의 컨베이어 벨트 동작 관련 api 입니다.")
@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    /*@Operation(summary = "공정 상태 조회", description = "공정, 벨트 등 공정의 상태를 조회합니다.")
    @GetMapping("/{factoryName}/{processName}")
    public ResponseEntity<?> showProcessStatus(
            @Parameter(description = "파라미터는 생성했던 공정의 이름을 입력합니다<br>ex) FrontProcess")
            @PathVariable ("factoryName") String factoryName,
            @PathVariable ("processName") String processName) {
        Process process;
        try{
            process = processService.showStatus(factoryName, processName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(process, HttpStatus.OK);
    }

    @Operation(summary = "공정 상태 업데이트", description = "공정, 벨트 등 공정의 상태를 업데이트 합니다. <br>모든 Key, Value를 입력하여 변경해도 가능하며 변경할 부분만 입력하여 변경하는 것도 가능합니다.<br>모든 Key에 대해서 Value의 값이 <br>  - run일 경우 Unity의 공정 혹은 벨트를 시작합니다.<br>  - strop일 경우 Unity의 공정 혹은 벨트를 정지합니다.")
    @PostMapping("/update/{factoryName}")
    public ResponseEntity<Void> updateProcess(
            @RequestBody @Valid ProcessDto processDto,
            @PathVariable ("factoryName") String factoryName) {
        processService.updateProcess(factoryName, processDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "공정 정지", description = "공정, 벨트 등 공정의 모든 장치를 정지 합니다.<br> 모든 Key에 대한 Value의 값을 전부 \"stop\"으로 변경합니다.")
    @PostMapping("/stop/{factoryName}")
    public ResponseEntity<Void> stopProcess(
            @RequestBody @Valid ProcessControlDto processControlDto,
            @PathVariable ("factoryName") String factoryName) {
        processService.stopProcess(factoryName, processControlDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "공정 시작", description = "공정, 벨트 등 공정의 모든 장치를 정지 합니다. <br> 모든 Key에 대한 Value의 값을 전부 \"run\"으로 변경합니다.")
    @PostMapping("/start/{factoryName}")
    public ResponseEntity<Void> startProcess(
            @RequestBody @Valid ProcessControlDto processControlDto,
            @PathVariable ("factoryName") String factoryName) {
        processService.startProcess(factoryName, processControlDto);
        return new ResponseEntity(HttpStatus.OK);
    }
*/


    @Operation(summary = "공정 상태 조회", description = "FactoryName과 ProcessName에 해당하는 공정의 모든 부속의 상태를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Process>> getProcessesByFactoryNameAndProcessName(
            @Parameter(description = "해당 파라미터는 factoryName의 값을 입력합니다. <br>ex) CarFactory")
            @RequestParam String factoryName,
            @Parameter(description = "해당 파라미터는 processName의 값을 입력합니다. <br>ex) FrontProcess")
            @RequestParam String processName
    ) {
        List<Process> processes = processService.getProcessesByFactoryNameAndProcessName(factoryName, processName);
        if (processes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(processes);
    }

    @Operation(summary = "공정 상태 개별 업데이트", description = "FactoryName과 ProcessName에 해당하는 공정의 모든 상태를 개별적으로 변경합니다." +
            "<br>동적 추가시 FactoryName과 ProcessName을 가지는 튜플이 없으면 생성하고 있으면 Update합니다." +
            "<br>한번에 모든 상태를 변경할 수 있습니다." +
            "<br>반드시 factoryName을 틀리지 않게 잘 적어주세요" +
            "<br>반드시 processName을 틀리지 않게 잘 적어주세요" +
            "<br>각 벨트에 해당하는 상태들을 입력해주세요" +
            "<br>ex)CarFactory" +
            "<br>ex)FrontProcess" +
            "<br>ex)stop or run")
    @PostMapping("/control")
    public ResponseEntity<String> addOrUpdateProcess(@RequestBody ProcessDto processDto) {
        processService.addOrUpdateProcess(processDto);
        return ResponseEntity.ok("Process added or updated successfully.");
    }

    @Operation(summary = "공정 상태 일괄 업데이트", description = "FactoryName과 ProcessName에 해당하는 공정의 모든 상태를 일괄적으로 변경합니다." +
            "<br>한번에 모든 상태를 변경할 수 있습니다.")
    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateProcessesByFactoryNameAndProcessName(
            @Parameter(description = "해당 파라미터는 factoryName의 값을 입력합니다. <br>ex) CarFactory")
            @RequestParam String factoryName,
            @Parameter(description = "해당 파라미터는 processName의 값을 입력합니다. <br>ex) FrontProcess")
            @RequestParam String processName,
            @Parameter(description = "해당 파라미터는 상태의 값을 입력합니다. <br>ex) stop")
            @RequestParam String status
    ) {
        processService.updateProcessesByFactoryNameAndProcessName(factoryName, processName, status);
        return ResponseEntity.ok("Processes updated successfully.");
    }
}
