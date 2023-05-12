package com.jit.backend.service;


import com.jit.backend.dto.ProcessControlDto;
import com.jit.backend.dto.ProcessDto;
import com.jit.backend.entity.Process;
import com.jit.backend.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProcessService {

    private final ProcessRepository processRepository;

    public List<Process> getProcessesByFactoryNameAndProcessName(String factoryName, String processName) {
        return processRepository.findByFactoryNameAndProcessName(factoryName, processName);
    }


    public void addOrUpdateProcess(ProcessDto processDto) {
        List<Process> existingProcesses = processRepository.findByFactoryNameAndProcessName(processDto.getFactoryName(), processDto.getProcessName());

        if (existingProcesses.isEmpty()) {
            // 데이터가 존재하지 않으면 새로운 데이터 추가
            Process newProcess = Process.builder()
                    .factoryName(processDto.getFactoryName())
                    .processName(processDto.getProcessName())
                    .processStatus(processDto.getProcessStatus())
                    .firstProcessMachineConveyorBelt(processDto.getFirstProcessMachineConveyorBelt())
                    .secondProcessMachineConveyorBelt(processDto.getSecondProcessMachineConveyorBelt())
                    .thirdProcessMachineConveyorBelt(processDto.getThirdProcessMachineConveyorBelt())
                    .fourthProcessMachineConveyorBelt(processDto.getFourthProcessMachineConveyorBelt())
                    .fifthProcessMachineConveyorBelt(processDto.getFifthProcessMachineConveyorBelt())
                    .sixthProcessMachineConveyorBelt(processDto.getSixthProcessMachineConveyorBelt())
                    .build();

            processRepository.save(newProcess);
        } else {
            // 데이터가 이미 존재하면 업데이트
            for (Process existingProcess : existingProcesses) {
                existingProcess.setProcessStatus(processDto.getProcessStatus());
                existingProcess.setFirstProcessMachineConveyorBelt(processDto.getFirstProcessMachineConveyorBelt());
                existingProcess.setSecondProcessMachineConveyorBelt(processDto.getSecondProcessMachineConveyorBelt());
                existingProcess.setThirdProcessMachineConveyorBelt(processDto.getThirdProcessMachineConveyorBelt());
                existingProcess.setFourthProcessMachineConveyorBelt(processDto.getFourthProcessMachineConveyorBelt());
                existingProcess.setFifthProcessMachineConveyorBelt(processDto.getFifthProcessMachineConveyorBelt());
                existingProcess.setSixthProcessMachineConveyorBelt(processDto.getSixthProcessMachineConveyorBelt());
                processRepository.save(existingProcess);
            }
        }
    }
    public void updateProcessesByFactoryNameAndProcessName(String factoryName, String processName, String status) {
        List<Process> processes = processRepository.findByFactoryNameAndProcessName(factoryName, processName);
        for (Process process : processes) {
            process.setProcessStatus(status);
            process.setFirstProcessMachineConveyorBelt(status);
            process.setSecondProcessMachineConveyorBelt(status);
            process.setThirdProcessMachineConveyorBelt(status);
            process.setFourthProcessMachineConveyorBelt(status);
            process.setFifthProcessMachineConveyorBelt(status);
            process.setSixthProcessMachineConveyorBelt(status);
        }
        processRepository.saveAll(processes);
    }


}
