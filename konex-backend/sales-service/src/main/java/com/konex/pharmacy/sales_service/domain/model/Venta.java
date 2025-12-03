package com.konex.pharmacy.sales_service.domain.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime fechaHora;

    @NotBlank
    private Long idMedicamento;

    @Positive
    private Integer cantidad;

    @DecimalMin("0.0")
    private Double valorUnitario;

    @DecimalMin("0.0")
    private Double valorTotal;
}
