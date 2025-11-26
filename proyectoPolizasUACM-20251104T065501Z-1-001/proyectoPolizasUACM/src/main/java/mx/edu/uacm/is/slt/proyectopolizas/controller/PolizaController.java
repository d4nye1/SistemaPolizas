package mx.edu.uacm.is.slt.proyectopolizas.controller;

import mx.edu.uacm.is.slt.proyectopolizas.model.Poliza;
import mx.edu.uacm.is.slt.proyectopolizas.repository.PolizaRepository;
import mx.edu.uacm.is.slt.proyectopolizas.service.ConsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    @Autowired
    private PolizaRepository polizaRepository;

    @Autowired
    private ConsistenciaService consistenciaService;

    // GET /polizas -> devuelve todas (prototipo)
    @GetMapping
    public List<Poliza> obtenerPolizas() {
        return polizaRepository.findAll();
    }

    // GET /polizas/{clave} -> devuelve una póliza por clave
    @GetMapping("/{clave}")
    public ResponseEntity<Poliza> obtenerPorClave(@PathVariable String clave) {
        Optional<Poliza> p = polizaRepository.findById(clave);
        return p.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST /polizas -> crea una póliza
    @PostMapping
    public ResponseEntity<Poliza> crearPoliza(@RequestBody Poliza poliza) {
        Poliza saved = polizaRepository.save(poliza);
        return ResponseEntity.ok(saved);
    }

    // PUT /polizas/{clave} -> actualiza
    @PutMapping("/{clave}")
    public ResponseEntity<Poliza> actualizarPoliza(@PathVariable String clave, @RequestBody Poliza detalles) {
        return polizaRepository.findById(clave).map(p -> {
            p.setTipo(detalles.getTipo());
            p.setMonto(detalles.getMonto());
            p.setDescripcion(detalles.getDescripcion());
            Poliza actualizado = polizaRepository.save(p);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /polizas/{clave}
    @DeleteMapping("/{clave}")
    public ResponseEntity<Object> eliminarPoliza(@PathVariable String clave) {
        return polizaRepository.findById(clave).map(p -> {
            polizaRepository.delete(p);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // Ejemplo de endpoint para verificar consistencia (reactivo 4.4)
    @GetMapping("/{clave}/verificar-consistencia")
    public ResponseEntity<Boolean> verificarConsistencia(@PathVariable String clave) {
        boolean ok = consistenciaService.verificarConsistenciaPorClave(clave);
        return ResponseEntity.ok(ok);
    }
}
