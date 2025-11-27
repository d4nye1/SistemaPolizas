package mx.edu.uacm.is.slt.as.sistpolizas.service;

import java.util.List;
import org.springframework.stereotype.Service;
import mx.edu.uacm.is.slt.as.sistpolizas.model.Poliza;
import mx.edu.uacm.is.slt.as.sistpolizas.model.Cliente;
import mx.edu.uacm.is.slt.as.sistpolizas.repository.PolizaRepository;
import mx.edu.uacm.is.slt.as.sistpolizas.repository.ClienteRepository;

@Service
public class PolizaService {

    private final PolizaRepository polizaRepository;
    private final ClienteRepository clienteRepository;

    public PolizaService(PolizaRepository polizaRepository, ClienteRepository clienteRepository) {
        this.polizaRepository = polizaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Poliza crearPoliza(Poliza poliza, String curpCliente) {
        Cliente cliente = clienteRepository.findById(curpCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        poliza.setCliente(cliente);
        return polizaRepository.save(poliza);
    }

    public Poliza actualizarMonto(Integer clave, Double monto) {
        Poliza poliza = polizaRepository.findById(clave)
                .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));

        poliza.setMonto(monto);
        return polizaRepository.save(poliza);
    }

    // Buscar por nombre del cliente
    public List<Poliza> obtenerPorNombreCliente(String nombres, String primerApellido) {
        if ((nombres == null || nombres.isEmpty()) && (primerApellido == null || primerApellido.isEmpty())) {
            return polizaRepository.findAll();
        }

        return polizaRepository
                .findByCliente_NombresContainingIgnoreCaseAndCliente_PrimerApellidoContainingIgnoreCase(
                        nombres != null ? nombres : "",
                        primerApellido != null ? primerApellido : "");
    }
    
    // ===== MÉTODOS COMPATIBLES CON LOS CONTROLLERS =====

// Obtiene UNA póliza por clave (para controllers REST)
public Poliza obtenerPorClavePoliza(Integer clave) {
    return polizaRepository.findById(clave).orElse(null);
}

//  Obtiene lista por clave (si deseas filtro)
public List<Poliza> obtenerPorClave(Integer clave) {
    if (clave == null) {
        return polizaRepository.findAll();
    }
    return List.of(polizaRepository.findById(clave).orElse(null));
}


    // Buscar por beneficiario
    public List<Poliza> obtenerPorNombreBeneficiario(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return polizaRepository.findAll();
        }
        return polizaRepository.buscarPorNombreBeneficiario(nombre);
    }

    public List<Poliza> obtenerTodas() {
        return polizaRepository.findAll();
    }

    // Obtener UNA póliza por ID
    public Poliza obtenerPolizaPorId(Integer id) {
        return polizaRepository.findById(id).orElse(null);
    }

    // Buscar por clave (lista)
    public List<Poliza> buscarPorClave(Integer clave) {
        if (clave == null) {
            return polizaRepository.findAll();
        }
        return polizaRepository.findByClave(clave);
    }

    public List<Poliza> obtenerPorTipo(String tipo) {
        if (tipo == null || tipo.isEmpty()) {
            return polizaRepository.findAll();
        }
        return polizaRepository.findByTipoContainingIgnoreCase(tipo);
    }

    public List<Poliza> obtenerPorCurpCliente(String curp) {
        if (curp == null || curp.isEmpty()) {
            return polizaRepository.findAll();
        }
        return polizaRepository.findByCliente_Curp(curp);
    }

    public List<Poliza> obtenerPorFechaNacimientoBeneficiario(java.time.LocalDate fecha) {
        if (fecha == null) {
            return polizaRepository.findAll();
        }
        return polizaRepository.buscarPorFechaNacimientoBeneficiario(fecha);
    }

    public void eliminar(Integer clave) {
        polizaRepository.deleteById(clave);
    }

    public Poliza guardarPoliza(Poliza poliza) {

        if (poliza.getCliente() == null || poliza.getCliente().getCurp() == null) {
            throw new RuntimeException("La CURP del cliente es obligatoria");
        }

        Cliente cliente = clienteRepository.findById(poliza.getCliente().getCurp())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        poliza.setCliente(cliente);

        return polizaRepository.save(poliza);
    }
}
