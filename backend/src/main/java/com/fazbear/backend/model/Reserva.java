package com.fazbear.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;      // Nombre completo
    private String email;       // Email
    private LocalDate fecha;    // Fecha de la reserva
    private Integer invitados; // Número de invitados
    private String mensaje;     // Pedidos especiales o mensaje

    public Reserva() {}

    public Reserva(String nombre, String email, LocalDate fecha, Integer invitados, String mensaje) {
        this.nombre = nombre;
        this.email = email;
        this.fecha = fecha;
        this.invitados = invitados;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Integer getInvitados() { return invitados; }
    public void setInvitados(Integer invitados) { this.invitados = invitados; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
