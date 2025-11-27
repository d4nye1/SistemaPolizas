package mx.edu.uacm.is.slt.as.sistpolizas.repository;

import mx.edu.uacm.is.slt.as.sistpolizas.model.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {

    //buscar beneficiario por nombre completo
    Optional<Beneficiario> findByNombresAndPrimerApellidoAndSegundoApellido(
            String nombres,
            String primerApellido,
            String segundoApellido
    );
}
