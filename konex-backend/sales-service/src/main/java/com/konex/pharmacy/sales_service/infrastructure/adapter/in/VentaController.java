package com.konex.pharmacy.sales_service.infrastructure.adapter.in;

import com.konex.pharmacy.sales_service.application.port.in.VentaUseCase;
import com.konex.pharmacy.sales_service.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaUseCase useCase;

    public VentaController(VentaUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Venta venta) {
        try {
            Venta saved = useCase.crearVenta(venta);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public Page<Venta> listar(@RequestParam(value = "desde", required = false) LocalDateTime desde,
                              @RequestParam(value = "hasta", required = false) LocalDateTime hasta,
                              Pageable pageable) {
        if (desde != null && hasta != null) {
            return useCase.filtrarPorFechaPaginado(desde, hasta, pageable);
        }
        return useCase.listarVentasPaginado(pageable);
    }
}