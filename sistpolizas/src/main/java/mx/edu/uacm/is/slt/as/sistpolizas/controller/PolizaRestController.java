package mx.edu.uacm.is.slt.as.sistpolizas.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mx.edu.uacm.is.slt.as.sistpolizas.model.Poliza;
import mx.edu.uacm.is.slt.as.sistpolizas.service.PolizaService;

@RestController
@RequestMapping("/api/polizas")
public class PolizaRestController {

    private final PolizaService polizaService;

    public PolizaRestController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @PostMapping
    public ResponseEntity<Poliza> crearPoliza(@RequestBody Poliza poliza) {
    Poliza nueva = polizaService.guardarPoliza(poliza);
    return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
}


    @GetMapping
    public List<Poliza> listarPolizas() {
        return polizaService.obtenerTodas();
    }

    @PutMapping("/{clave}")
    public ResponseEntity<Poliza> actualizarPoliza(@PathVariable Integer clave, @RequestBody Poliza datos) {
        Poliza actualizada = polizaService.actualizarMonto(clave, datos.getMonto());
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{clave}")
    public ResponseEntity<Void> eliminarPoliza(@PathVariable Integer clave) {
        polizaService.eliminar(clave);
        return ResponseEntity.noContent().build();
    }
    
    // Buscar pólizas con filtros opcionales (soporta curp, cliente, beneficiario, clave, tipo, fechaNacimiento)
@GetMapping("/buscar")
public List<Poliza> buscarPolizas(
        @RequestParam(required = false) String curp,
        @RequestParam(required = false) String cliente,
        @RequestParam(required = false) String beneficiario,
        @RequestParam(required = false) Integer clave,
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) String fechaNacimiento
) {

    // 1. Prioridad: clave exacta
    if (clave != null) {
    Poliza p = polizaService.obtenerPorClavePoliza(clave);
    return p != null ? java.util.Collections.singletonList(p) : java.util.Collections.emptyList();
}


    // 2. CURP exacta
    if (curp != null && !curp.isEmpty()) {
        return polizaService.obtenerPorCurpCliente(curp);
    }

    // 3. Tipo de póliza (parcial)
    if (tipo != null && !tipo.isEmpty()) {
        return polizaService.obtenerPorTipo(tipo);
    }

    // 4. Fecha de nacimiento del beneficiario
    if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
        java.time.LocalDate f;
        try {
            f = java.time.LocalDate.parse(fechaNacimiento);
        } catch (Exception e) {
            return java.util.Collections.emptyList();
        }
        return polizaService.obtenerPorFechaNacimientoBeneficiario(f);
    }

    // 5. Cliente
    if (cliente != null && !cliente.isEmpty()) {
        String[] partes = cliente.split(" ", 2);
        String nombres = partes[0];
        String primerApellido = partes.length > 1 ? partes[1] : "";
        return polizaService.obtenerPorNombreCliente(nombres, primerApellido);
    }

    // 6. Beneficiario
    if (beneficiario != null && !beneficiario.isEmpty()) {
        return polizaService.obtenerPorNombreBeneficiario(beneficiario);
    }

    // 7. Si no hay filtros, regresar todas
    return polizaService.obtenerTodas();
}

}
