package com.jit.backend.entity;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.dto.SalesDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sales")
@NoArgsConstructor
public class Sales {
    @Id
    @Column(name = "salesId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String factoryName;

    @Column
    private LocalDate date;

    @Column
    private Long sales;

    @Column
    private Long success;

    @Column
    private Long fail;

    @Column
    private Long count;

}
