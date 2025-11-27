package mx.edu.uacm.is.slt.as.sistpolizas.service;

import mx.edu.uacm.is.slt.as.sistpolizas.model.Cliente;
import mx.edu.uacm.is.slt.as.sistpolizas.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    // Listar todos
    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    // Buscar por CURP
    public Optional<Cliente> buscarPorCurp(String curp) {
        return repo.findById(curp);
    }

    // Crear cliente (lanza IllegalArgumentException si ya existe)
    @Transactional
    public Cliente crear(Cliente cliente) {
        if (cliente.getCurp() == null || cliente.getCurp().isBlank()) {
            throw new IllegalArgumentException("CURP obligatoria");
        }
        if (repo.existsById(cliente.getCurp())) {
            throw new IllegalArgumentException("Ya existe un cliente con esa CURP");
        }
        return repo.save(cliente);
    }

    // Actualizar (busca por curp y actualiza campos permitidos)
    @Transactional
    public Cliente actualizar(String curp, Cliente datos) {
        Cliente existente = repo.findById(curp)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con CURP: " + curp));

        existente.setDireccion(datos.getDireccion());
        existente.setFechaNacimiento(datos.getFechaNacimiento());
        existente.setNombres(datos.getNombres());
        existente.setPrimerApellido(datos.getPrimerApellido());
        existente.setSegundoApellido(datos.getSegundoApellido());

        return repo.save(existente);
    }

    // Eliminar por CURP
    @Transactional
    public void eliminar(String curp) {
        if (!repo.existsById(curp)) {
            throw new IllegalArgumentException("Cliente no encontrado con CURP: " + curp);
        }
        repo.deleteById(curp);
    }
}
