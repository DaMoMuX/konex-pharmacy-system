package com.konex.pharmacy.inventory_service.infrastructure.adapter.in;

import com.konex.pharmacy.inventory_service.application.port.in.MedicamentoUseCase;
import com.konex.pharmacy.inventory_service.domain.exception.StockInsuficienteException;
import com.konex.pharmacy.inventory_service.domain.model.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoUseCase useCase;

    public MedicamentoController(MedicamentoUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public Page<Medicamento> list(@RequestParam(required = false) String nombre, Pageable pageable) {
        return useCase.listarPaginadoFiltrado(nombre != null ? nombre : "", pageable);
    }

    @PostMapping
    public Medicamento create(@RequestBody Medicamento medicamento) {
        return useCase.crear(medicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> update(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        try {
            return ResponseEntity.ok(useCase.actualizar(id, medicamento));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/descontar-stock")
    public ResponseEntity<Medicamento> descontarStock(@PathVariable Long id, @RequestParam int cantidad) {
        try {
            Medicamento result = useCase.descontarStock(id, cantidad);
            return ResponseEntity.ok(result);
        } catch (StockInsuficienteException e) {
            return ResponseEntity.status(409).body(null);
        }
    }
}
