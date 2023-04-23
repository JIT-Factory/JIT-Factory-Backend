package com.jit.backend.service;


import com.jit.backend.dto.ProcessControlDto;
import com.jit.backend.dto.ProcessDto;
import com.jit.backend.entity.Process;
import com.jit.backend.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProcessService {

    private final ProcessRepository processRepository;

    public Process showStatus(String processName){
        Process process = processRepository.findByProcessName(processName );
        return process;
    }

    public void updateProcess(ProcessDto processDto) {
        Process process = processRepository.findByProcessName(processDto.getProcessName());
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

    public void stopProcess(ProcessControlDto processControlDto) {
        Process process = processRepository.findByProcessName(processControlDto.getProcessName());
        if(process != null){
            process.status("stop");
        }
        processRepository.saveAndFlush(process);
    }

    public void startProcess(ProcessControlDto processControlDto) {
        Process process = processRepository.findByProcessName(processControlDto.getProcessName());
        if(process != null){
            process.status("start");
        }
        processRepository.saveAndFlush(process);
    }
}
