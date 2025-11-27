package mx.edu.uacm.is.slt.as.sistpolizas.controller;

import mx.edu.uacm.is.slt.as.sistpolizas.model.Beneficiario;
import mx.edu.uacm.is.slt.as.sistpolizas.service.BeneficiarioService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/beneficiarios")
@CrossOrigin(origins = "*")
public class BeneficiarioController {

    private final BeneficiarioService service;

    public BeneficiarioController(BeneficiarioService service) {
        this.service = service;
    }

    // LISTAR
    @GetMapping
    public Iterable<Beneficiario> listar() {
        return service.listar();
    }

    // CREAR
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Beneficiario crear(@RequestBody Beneficiario beneficiario) {
        // No pasamos la clave aquí; el service la obtiene/valida internamente
        return service.crear(beneficiario);
    }

    // ACTUALIZAR
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Beneficiario actualizar(
            @PathVariable Integer id,
            @RequestBody Beneficiario beneficiario) {
        // Igual: el service recibe (id, beneficiario)
        return service.actualizar(id, beneficiario);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.borrar(id);
    }

    // BUSCAR
    @GetMapping("/buscar")
    public Beneficiario buscarPorNombre(
        @RequestParam String nombres,
        @RequestParam String primerApellido,
        @RequestParam(required = false) String segundoApellido) {

        return service.buscarPorDatos(nombres, primerApellido, segundoApellido)
            .orElse(null);
}
    
}
