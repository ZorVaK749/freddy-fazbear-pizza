package com.fazbear.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "animatronics")
public class Animatronic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role; // Ej: "Líder", "Guitarrista", etc.
    private String description;
    private String imageUrl; // URL o nombre de archivo de la imagen

    public Animatronic() {}

    public Animatronic(String name, String role, String description, String imageUrl) {
        this.name = name;
        this.role = role;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
