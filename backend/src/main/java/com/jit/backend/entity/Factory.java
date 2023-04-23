package com.jit.backend.entity;

import com.jit.backend.dto.FactoryDto;
import com.jit.backend.dto.OrdersDto;
import com.jit.backend.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "factory")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Factory {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String processName;

    @Column
    private String factoryStatus;

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
        this.factoryStatus = status;
        this.conveyorBeltWheel = status;
        this.conveyorBeltDoor = status;
        this.firstProcessMachineConveyorBelt = status;
        this.secondProcessMachineConveyorBelt = status;
        this.thirdProcessMachineConveyorBelt = status;
        this.fourthProcessMachineConveyorBelt = status;
    }
}
