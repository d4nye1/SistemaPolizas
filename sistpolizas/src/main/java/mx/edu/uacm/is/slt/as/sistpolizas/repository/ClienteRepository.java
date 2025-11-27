/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package mx.edu.uacm.is.slt.as.sistpolizas.repository;

import mx.edu.uacm.is.slt.as.sistpolizas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    // Si en el futuro necesitas búsquedas adicionales, agrégalas aquí.
}
