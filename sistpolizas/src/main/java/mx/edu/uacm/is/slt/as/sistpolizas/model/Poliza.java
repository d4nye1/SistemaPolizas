package mx.edu.uacm.is.slt.as.sistpolizas.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "poliza")
public class Poliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clave;

    @Column(nullable = false)
    @NotNull
    @Size(max = 100)
    private String tipo;

    @Column(nullable = false)
    @NotNull
    private Double monto;

    @Column(length = 1000)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "curp_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "poliza", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Beneficiario> beneficiarios = new ArrayList<>();

    // Getters y Setters

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    // helper para mantener la relación bidireccional correctamente
    public void addBeneficiario(Beneficiario b) {
        beneficiarios.add(b);
        b.setPoliza(this);
    }

    public void removeBeneficiario(Beneficiario b) {
        beneficiarios.remove(b);
        b.setPoliza(null);
    }
}
