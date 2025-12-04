package com.konex.pharmacy.sales_service.domain.model;

import lombok.Data;

@Data
public class MedicamentoDto {
    private Long id;
    private String nombre;
    private Double valorUnitario;
}