package mx.edu.uacm.is.slt.proyectopolizas.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private Double porcentaje;

    @ManyToOne
    @JoinColumn(name = "poliza_clave")
    private Poliza poliza;

    public Beneficiario() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }
    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }
    public Poliza getPoliza() { return poliza; }
    public void setPoliza(Poliza poliza) { this.poliza = poliza; }
}
