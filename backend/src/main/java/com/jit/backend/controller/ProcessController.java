package com.jit.backend.controller;

import com.jit.backend.dto.ProcessDto;
import com.jit.backend.entity.Process;
import com.jit.backend.service.ProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "04. 공정 관리 페이지", description = "제작 공정과 내부의 컨베이어 벨트 동작 관련 api 입니다.")
@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @Operation(summary = "공정 상태 조회", description = "모든 공장의 모든 공정의 상태를 조회합니다.")
    @GetMapping("/show")
    public ResponseEntity<List<Process>> getProcessesByFactoryName(){
        List<Process> processes = processService.allProcess();
        if (processes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(processes);
    }

    @Operation(summary = "공정 상태 조회", description = "FactoryName에 해당하는 공장의 모든 부속의 상태를 조회합니다.")
    @GetMapping("/show/factory")
    public ResponseEntity<List<Process>> getProcesses(
            @Parameter(description = "해당 파라미터는 factoryName의 값을 입력합니다. <br>ex) CarFactory")
            @RequestParam String factoryName
    ) {
        List<Process> processes = processService.getProcessesByFactoryName(factoryName);
        if (processes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(processes);
    }

    @Operation(summary = "공정 상태 조회", description = "FactoryName과 ProcessName에 해당하는 공정의 모든 부속의 상태를 조회합니다.")
    @GetMapping("/show/factory/process")
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


    @Operation(summary = "공정 추가 및 상태 개별 업데이트", description = "FactoryName과 ProcessName에 해당하는 공정의 모든 상태를 개별적으로 변경합니다." +
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
