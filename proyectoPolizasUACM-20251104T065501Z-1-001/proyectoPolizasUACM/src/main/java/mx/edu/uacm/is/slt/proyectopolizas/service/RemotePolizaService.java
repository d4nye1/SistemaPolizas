package mx.edu.uacm.is.slt.proyectopolizas.service;

import mx.edu.uacm.is.slt.proyectopolizas.model.Poliza;
import org.springframework.stereotype.Service;

/**
 * Servicio simulado que representa el repositorio remoto dueño de pólizas.
 * En este prototipo retorna objetos que coinciden con la versión local para
 * facilitar las pruebas de consistencia.
 */
@Service
public class RemotePolizaService {

    public Poliza obtenerPolizaPorClave(String clave) {
        // En un prototipo, podríamos devolver null o un objeto construido.
        // Aquí construimos un objeto de ejemplo para simular respuesta del remoto.
        Poliza p = new Poliza();
        p.setClave(clave);
        p.setTipo(0);
        p.setMonto(10000.0);
        p.setDescripcion("Póliza simulada (remota)");
        return p;
    }
}
