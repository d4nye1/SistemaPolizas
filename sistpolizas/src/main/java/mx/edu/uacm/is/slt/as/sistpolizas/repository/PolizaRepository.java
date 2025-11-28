package mx.edu.uacm.is.slt.as.sistpolizas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.edu.uacm.is.slt.as.sistpolizas.model.Poliza;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Integer> {

    // Buscar por tipo (parcial y case-insensitive)
    List<Poliza> findByTipoContainingIgnoreCase(String tipo);

    // Buscar polizas por curp del cliente (cliente.curp)
    List<Poliza> findByCliente_Curp(String curp);

    // Buscar por nombres del cliente y primer apellido
    List<Poliza> findByCliente_NombresAndCliente_PrimerApellido(String nombres, String primerApellido);

    // Buscar por tipo usando parte del texto
    List<Poliza> findByTipoContaining(String texto);

    // Buscar por nombre de beneficiario (búsqueda parcial)
    List<Poliza> findByBeneficiarios_NombresContaining(String nombre);
    
    // Cliente: búsqueda parcial, sin importar mayúsculas
    List<Poliza> findByCliente_NombresContainingIgnoreCaseAndCliente_PrimerApellidoContainingIgnoreCase(String nombres, String primerApellido);

    // Beneficiario: búsqueda parcial, sin importar mayúsculas
    List<Poliza> findByBeneficiarios_NombresContainingIgnoreCase(String nombres);
    
    // Buscar por clave de póliza (nota: JpaRepository ya tiene findById, pero puedes exponer este)
    List<Poliza> findByClave(Integer clave);
    
    // Buscar por fecha de nacimiento del beneficiario (JPQL)
    @Query("SELECT DISTINCT p FROM Poliza p JOIN p.beneficiarios b WHERE b.fechaNacimiento = :fecha")
    List<Poliza> buscarPorFechaNacimientoBeneficiario(@Param("fecha") LocalDate fecha);

    // Mejor JPQL para nombre beneficiario (parcial, case-insensitive) — usa LEFT JOIN para no perder pólizas si quisieras combinar
    @Query("SELECT DISTINCT p FROM Poliza p LEFT JOIN p.beneficiarios b WHERE UPPER(b.nombres) LIKE CONCAT('%', UPPER(:nombre), '%')")
    List<Poliza> buscarPorNombreBeneficiario(@Param("nombre") String nombre);

    
}
