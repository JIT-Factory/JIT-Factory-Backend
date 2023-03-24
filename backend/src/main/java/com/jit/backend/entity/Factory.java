package com.jit.backend.entity;

import com.jit.backend.dto.FactoryDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "factory")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Factory {
    @Id
    @Column(name = "factory")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @Column
    private String reason;

    @Column
    private LocalDateTime errorTime;

    public static Factory addLogs(FactoryDto factoryDto) {
        Factory factory = new Factory();
        factory.status = factoryDto.getStatus();
        factory.reason = factoryDto.getReason();
        factory.errorTime = LocalDateTime.now();
        return factory;
    }
}
