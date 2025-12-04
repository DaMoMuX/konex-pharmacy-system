package com.konex.pharmacy.sales_service.application.port.out;

import com.konex.pharmacy.sales_service.domain.model.MedicamentoDto;

public interface InventoryClientPort {
    MedicamentoDto descontarStockYObtenerDatos(Long medicamentoId, int cantidad);
}