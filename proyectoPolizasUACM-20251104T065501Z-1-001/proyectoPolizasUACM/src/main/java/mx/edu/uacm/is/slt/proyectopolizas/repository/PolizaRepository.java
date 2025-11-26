package mx.edu.uacm.is.slt.proyectopolizas.repository;

import mx.edu.uacm.is.slt.proyectopolizas.model.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PolizaRepository extends JpaRepository<Poliza, String> {
    List<Poliza> findByTipo(Integer tipo);
    List<Poliza> findByClienteCurp(String curp); // will need JPQL or implementation if used
}
