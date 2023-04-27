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
                    .conveyorBeltWheel(processDto.getConveyorBeltWheel())
                    .conveyorBeltDoor(processDto.getConveyorBeltDoor())
                    .firstProcessMachineConveyorBelt(processDto.getFirstProcessMachineConveyorBelt())
                    .secondProcessMachineConveyorBelt(processDto.getSecondProcessMachineConveyorBelt())
                    .thirdProcessMachineConveyorBelt(processDto.getThirdProcessMachineConveyorBelt())
                    .fourthProcessMachineConveyorBelt(processDto.getFourthProcessMachineConveyorBelt())
                    .build();

            processRepository.save(newProcess);
        } else {
            // 데이터가 이미 존재하면 업데이트
            for (Process existingProcess : existingProcesses) {
                existingProcess.setProcessStatus(processDto.getProcessStatus());
                existingProcess.setConveyorBeltWheel(processDto.getConveyorBeltWheel());
                existingProcess.setConveyorBeltDoor(processDto.getConveyorBeltDoor());
                existingProcess.setFirstProcessMachineConveyorBelt(processDto.getFirstProcessMachineConveyorBelt());
                existingProcess.setSecondProcessMachineConveyorBelt(processDto.getSecondProcessMachineConveyorBelt());
                existingProcess.setThirdProcessMachineConveyorBelt(processDto.getThirdProcessMachineConveyorBelt());
                existingProcess.setFourthProcessMachineConveyorBelt(processDto.getFourthProcessMachineConveyorBelt());

                processRepository.save(existingProcess);
            }
        }
    }
    public void updateProcessesByFactoryNameAndProcessName(String factoryName, String processName, String status) {
        List<Process> processes = processRepository.findByFactoryNameAndProcessName(factoryName, processName);
        for (Process process : processes) {
            process.setProcessStatus(status);
            process.setConveyorBeltWheel(status);
            process.setConveyorBeltDoor(status);
            process.setFirstProcessMachineConveyorBelt(status);
            process.setSecondProcessMachineConveyorBelt(status);
            process.setThirdProcessMachineConveyorBelt(status);
            process.setFourthProcessMachineConveyorBelt(status);
        }
        processRepository.saveAll(processes);
    }



    /*public Process showStatus(String factoryName, String processName){
        Process process = processRepository.findByFactoryNameAndProcessName(factoryName, processName);
        return process;
    }

    public void updateProcess(String factoryName, ProcessDto processDto) {
        Process process = processRepository.findByFactoryNameAndProcessName(factoryName, processDto.getProcessName());
        if(process == null){
            process = Process.builder()
                    .processName(processDto.getProcessName())
                    .processStatus(processDto.getProcessStatus())
                    .conveyorBeltWheel(processDto.getConveyorBeltWheel())
                    .conveyorBeltDoor(processDto.getConveyorBeltDoor())
                    .firstProcessMachineConveyorBelt(processDto.getFirstProcessMachineConveyorBelt())
                    .secondProcessMachineConveyorBelt(processDto.getSecondProcessMachineConveyorBelt())
                    .thirdProcessMachineConveyorBelt(processDto.getThirdProcessMachineConveyorBelt())
                    .fourthProcessMachineConveyorBelt(processDto.getFourthProcessMachineConveyorBelt())
                    .build();
        }else{
            if(processDto.getProcessName() != null){
                process.setProcessName(processDto.getProcessName());
            }
            if(processDto.getConveyorBeltWheel() != null){
                process.setConveyorBeltWheel(processDto.getConveyorBeltWheel());
            }
            if(processDto.getConveyorBeltDoor() != null){
                process.setConveyorBeltDoor(processDto.getConveyorBeltDoor());
            }
            if(processDto.getFirstProcessMachineConveyorBelt() != null){
                process.setFirstProcessMachineConveyorBelt(processDto.getFirstProcessMachineConveyorBelt());
            }
            if(processDto.getSecondProcessMachineConveyorBelt() != null){
                process.setSecondProcessMachineConveyorBelt(processDto.getSecondProcessMachineConveyorBelt());
            }
            if(processDto.getThirdProcessMachineConveyorBelt() != null){
                process.setThirdProcessMachineConveyorBelt(processDto.getThirdProcessMachineConveyorBelt());
            }
            if(processDto.getFourthProcessMachineConveyorBelt() != null){
                process.setFourthProcessMachineConveyorBelt(processDto.getFourthProcessMachineConveyorBelt());
            }
        }
        processRepository.saveAndFlush(process);
    }

    public void stopProcess(String factoryName, ProcessControlDto processControlDto) {
        Process process = processRepository.findByFactoryNameAndProcessName(factoryName, processControlDto.getProcessName());
        if(process != null){
            process.status("stop");
        }
        processRepository.saveAndFlush(process);
    }

    public void startProcess(String factoryName, ProcessControlDto processControlDto) {
        Process process = processRepository.findByFactoryNameAndProcessName(factoryName, processControlDto.getProcessName());
        if(process != null){
            process.status("start");
        }
        processRepository.saveAndFlush(process);
    }*/
}
