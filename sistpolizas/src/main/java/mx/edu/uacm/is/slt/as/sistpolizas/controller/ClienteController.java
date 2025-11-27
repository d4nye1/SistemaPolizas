package mx.edu.uacm.is.slt.as.sistpolizas.controller;

import mx.edu.uacm.is.slt.as.sistpolizas.model.Cliente;
import mx.edu.uacm.is.slt.as.sistpolizas.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Validated
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // GET /api/clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /api/clientes/{curp}
    @GetMapping("/{curp}")
    public ResponseEntity<Cliente> obtener(@PathVariable String curp) {
        return service.buscarPorCurp(curp)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/clientes  (cuerpo JSON con la entidad completa)
    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        Cliente creado = service.crear(cliente);
        URI location = URI.create(String.format("/api/clientes/%s", creado.getCurp()));
        return ResponseEntity.created(location).body(creado);
    }

    // PUT /api/clientes/{curp} (cuerpo JSON con campos a actualizar)
    @PutMapping("/{curp}")
    public ResponseEntity<Cliente> actualizar(@PathVariable String curp, @Valid @RequestBody Cliente datos) {
        try {
            Cliente actualizado = service.actualizar(curp, datos);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/clientes/{curp}
    @DeleteMapping("/{curp}")
    public ResponseEntity<Void> eliminar(@PathVariable String curp) {
        try {
            service.eliminar(curp);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
