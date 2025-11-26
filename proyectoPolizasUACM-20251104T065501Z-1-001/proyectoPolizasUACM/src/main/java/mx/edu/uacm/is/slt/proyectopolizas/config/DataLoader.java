package mx.edu.uacm.is.slt.proyectopolizas.config;

import mx.edu.uacm.is.slt.proyectopolizas.model.Cliente;
import mx.edu.uacm.is.slt.proyectopolizas.model.Poliza;
import mx.edu.uacm.is.slt.proyectopolizas.model.Beneficiario;
import mx.edu.uacm.is.slt.proyectopolizas.repository.ClienteRepository;
import mx.edu.uacm.is.slt.proyectopolizas.repository.PolizaRepository;
import mx.edu.uacm.is.slt.proyectopolizas.repository.BeneficiarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Collections;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(ClienteRepository clienteRepo, PolizaRepository polizaRepo, BeneficiarioRepository benRepo) {
        return args -> {
            Cliente c = new Cliente();
            c.setCurp("AAAA010101HDFRRL09");
            c.setNombres("Juan");
            c.setPrimerApellido("Pérez");
            c.setSegundoApellido("Gómez");
            c.setDireccion("Calle Falsa 123");
            c.setFechaNacimiento(LocalDate.of(1990,1,1));

            Poliza p = new Poliza();
            p.setClave("POL-0001");
            p.setTipo(0);
            p.setMonto(10000.0);
            p.setDescripcion("Seguro de auto");
            p.setCliente(c);

            Beneficiario b = new Beneficiario();
            b.setNombres("María");
            b.setPrimerApellido("Pérez");
            b.setFechaNacimiento(LocalDate.of(2010,5,20));
            b.setPorcentaje(100.0);
            b.setPoliza(p);

            // relationships
            c.setPolizas(Collections.singletonList(p));
            p.setBeneficiarios(Collections.singletonList(b));

            clienteRepo.save(c);
            // polizaRepo.save(p); // cascade from cliente will persist poliza and beneficiario
        };
    }
}
