package mx.edu.uacm.is.slt.as.sistpolizas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mx.edu.uacm.is.slt.as.sistpolizas.model.Poliza;
import mx.edu.uacm.is.slt.as.sistpolizas.service.PolizaService;

@Controller
@RequestMapping("/polizas")
public class PolizaController {

    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    // Mostrar listado de pólizas en HTML
    @GetMapping
    public String listarPolizas(Model model) {
        List<Poliza> polizas = polizaService.obtenerTodas();
        model.addAttribute("polizas", polizas);
        return "lista-polizas";  // HTML
    }

    // Mostrar formulario para crear nueva póliza
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaPoliza(Model model) {
        model.addAttribute("poliza", new Poliza());
        return "form-poliza"; // HTML del formulario
    }

    // Guardar póliza desde formulario
    @PostMapping("/guardar")
    public String guardarPoliza(
            @ModelAttribute Poliza poliza,
            @RequestParam("curpCliente") String curpCliente
    ) {
        polizaService.crearPoliza(poliza, curpCliente);  // usa método de servicio que recibe CURP
        return "redirect:/polizas";  // redirige al listado
    }

    // Mostrar detalles de póliza
    @GetMapping("/{clave}")
    public String buscarPolizaPorClave(@PathVariable Integer clave, Model model) {
        Poliza poliza = polizaService.obtenerPorClavePoliza(clave);
        model.addAttribute("poliza", poliza);
        return "detalle-poliza"; // HTML de detalles
    }

    // Eliminar póliza desde vista
    @GetMapping("/eliminar/{clave}")
    public String eliminarPoliza(@PathVariable Integer clave) {
        polizaService.eliminar(clave);
        return "redirect:/polizas";
    }
}
