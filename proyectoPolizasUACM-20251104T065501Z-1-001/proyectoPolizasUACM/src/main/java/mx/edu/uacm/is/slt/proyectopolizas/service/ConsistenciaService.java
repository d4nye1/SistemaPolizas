package mx.edu.uacm.is.slt.proyectopolizas.service;

import mx.edu.uacm.is.slt.proyectopolizas.model.Poliza;
import mx.edu.uacm.is.slt.proyectopolizas.repository.PolizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsistenciaService {

    @Autowired
    private PolizaRepository localRepository;

    @Autowired
    private RemotePolizaService remoteService;

    /**
     * Verifica la consistencia entre la póliza local y la remota por clave.
     * Retorna true si existen ambas y coinciden en algunos campos principales.
     */
    public boolean verificarConsistenciaPorClave(String clave) {
        Optional<Poliza> local = localRepository.findById(clave);
        Poliza remoto = remoteService.obtenerPolizaPorClave(clave);

        if(local.isPresent() && remoto != null) {
            Poliza l = local.get();
            // Comparación simple de ejemplo
            boolean tipoEq = (l.getTipo() == null ? remoto.getTipo() == null : l.getTipo().equals(remoto.getTipo()));
            boolean montoEq = (l.getMonto() == null ? remoto.getMonto() == null : l.getMonto().equals(remoto.getMonto()));
            return tipoEq && montoEq;
        }
        return false;
    }
}
