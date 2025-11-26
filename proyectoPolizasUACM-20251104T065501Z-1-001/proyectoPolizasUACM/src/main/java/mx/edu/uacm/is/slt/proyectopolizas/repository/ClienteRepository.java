package mx.edu.uacm.is.slt.proyectopolizas.repository;

import mx.edu.uacm.is.slt.proyectopolizas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
