package com.jit.backend.entity;

import com.jit.backend.dto.MaterialDto;
import com.jit.backend.dto.OrdersDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "material")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Material {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String materialName;

    @Column
    private Integer stock;


    public void updateMaterial(MaterialDto materialDto) {
        this.stock += materialDto.getStock();
    }
}
