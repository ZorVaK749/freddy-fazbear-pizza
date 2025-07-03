package com.fazbear.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Nombre del responsable de la reserva
    private String email;
    private LocalDate date; // Fecha del evento
    private Integer guests; // Número de invitados
    private String message; // Mensaje o pedidos especiales

    public Event() {}

    public Event(String name, String email, LocalDate date, Integer guests, String message) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.guests = guests;
        this.message = message;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getGuests() { return guests; }
    public void setGuests(Integer guests) { this.guests = guests; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
