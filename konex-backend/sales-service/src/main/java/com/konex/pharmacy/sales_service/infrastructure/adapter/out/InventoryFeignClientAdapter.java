package com.konex.pharmacy.sales_service.infrastructure.adapter.out;

import com.konex.pharmacy.sales_service.application.port.out.InventoryClientPort;
import com.konex.pharmacy.sales_service.domain.model.MedicamentoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
interface FeignInventoryApi {
    @PutMapping("/medicamentos/{id}/descontar-stock")
    MedicamentoDto descontarStock(@PathVariable("id") Long medicamentoId, @RequestParam("cantidad") int cantidad);
}

@Component
public class InventoryFeignClientAdapter implements InventoryClientPort {

    private final FeignInventoryApi feignInventoryApi;

    public InventoryFeignClientAdapter(FeignInventoryApi feignInventoryApi) {
        this.feignInventoryApi = feignInventoryApi;
    }

    @Override
    public MedicamentoDto descontarStockYObtenerDatos(Long medicamentoId, int cantidad) {
        return feignInventoryApi.descontarStock(medicamentoId, cantidad);
    }
}