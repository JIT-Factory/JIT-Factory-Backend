package com.jit.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "process")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Process {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String processName;

    @Column
    private String processStatus;

    @Column
    private String conveyorBeltWheel;

    @Column
    private String conveyorBeltDoor;

    @Column
    private String firstProcessMachineConveyorBelt;

    @Column
    private String secondProcessMachineConveyorBelt;

    @Column
    private String thirdProcessMachineConveyorBelt;

    @Column
    private String fourthProcessMachineConveyorBelt;


    public void status(String status) {
        this.processStatus = status;
        this.conveyorBeltWheel = status;
        this.conveyorBeltDoor = status;
        this.firstProcessMachineConveyorBelt = status;
        this.secondProcessMachineConveyorBelt = status;
        this.thirdProcessMachineConveyorBelt = status;
        this.fourthProcessMachineConveyorBelt = status;
    }
}
