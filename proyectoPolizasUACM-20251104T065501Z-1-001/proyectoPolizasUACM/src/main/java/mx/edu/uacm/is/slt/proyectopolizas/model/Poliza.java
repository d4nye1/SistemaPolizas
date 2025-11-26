package mx.edu.uacm.is.slt.proyectopolizas.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Poliza {
    @Id
    private String clave; // clave única de la póliza

    private Integer tipo; // 0=Auto,1=Vida,2=Médico
    private Double monto;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cliente_curp")
    private Cliente cliente;

    @OneToMany(mappedBy = "poliza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Beneficiario> beneficiarios;

    public Poliza() {}

    // Getters and setters
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    public Integer getTipo() { return tipo; }
    public void setTipo(Integer tipo) { this.tipo = tipo; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<Beneficiario> getBeneficiarios() { return beneficiarios; }
    public void setBeneficiarios(List<Beneficiario> beneficiarios) { this.beneficiarios = beneficiarios; }
}
