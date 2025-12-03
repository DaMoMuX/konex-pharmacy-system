package com.konex.pharmacy.inventory_service.domain;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String laboratorio;

    @NotNull
    private LocalDate fechaFabricacion;

    @NotNull
    private LocalDate fechaVencimiento;

    @Min(0)
    private Integer cantidadStock;

    @DecimalMin("0.0")
    private Double valorUnitario;
}
