package mx.edu.uacm.is.slt.as.sistpolizas.service;

import java.util.Optional;
import mx.edu.uacm.is.slt.as.sistpolizas.model.Beneficiario;
import mx.edu.uacm.is.slt.as.sistpolizas.model.Poliza;
import mx.edu.uacm.is.slt.as.sistpolizas.repository.BeneficiarioRepository;
import mx.edu.uacm.is.slt.as.sistpolizas.repository.PolizaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final PolizaRepository polizaRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, PolizaRepository polizaRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.polizaRepository = polizaRepository;
    }

    // LISTAR
    public Iterable<Beneficiario> listar() {
        return beneficiarioRepository.findAll();
    }

    // CREAR
    public Beneficiario crear(Beneficiario beneficiario) {
        if (beneficiario.getPoliza() != null && beneficiario.getPoliza().getClave() != null) {
            Poliza poliza = polizaRepository
                    .findById(beneficiario.getPoliza().getClave())
                    .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));
            beneficiario.setPoliza(poliza);
        }
        return beneficiarioRepository.save(beneficiario);
    }

    // ACTUALIZAR
    public Beneficiario actualizar(Integer id, Beneficiario beneficiario) {
        Beneficiario existente = beneficiarioRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiario no encontrado"));

        existente.setNombres(beneficiario.getNombres());
        existente.setPrimerApellido(beneficiario.getPrimerApellido());
        existente.setSegundoApellido(beneficiario.getSegundoApellido());
        existente.setFechaNacimiento(beneficiario.getFechaNacimiento());
        existente.setPorcentaje(beneficiario.getPorcentaje());

        if (beneficiario.getPoliza() != null && beneficiario.getPoliza().getClave() != null) {
            Poliza poliza = polizaRepository
                    .findById(beneficiario.getPoliza().getClave())
                    .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));
            existente.setPoliza(poliza);
        }

        return beneficiarioRepository.save(existente);
    }

    // BORRAR
    public void borrar(Integer id) {
        beneficiarioRepository.deleteById(id);
    }

    // BUSCAR
    public Optional<Beneficiario> buscar(Integer id) {
        return beneficiarioRepository.findById(id);
    }
    // BUSCAR POR CURP CLIENTE
    public Optional<Beneficiario> buscarPorDatos(String nombres, String primer, String segundo) {
    return beneficiarioRepository
            .findByNombresAndPrimerApellidoAndSegundoApellido(
                    nombres, primer, segundo);
}

}


