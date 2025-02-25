package com.proyecto_inventario.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user") 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Evita nombres de usuario repetidos
    private String username;

    @Column(nullable = false) // No permite contraseñas nulas
    private String password;

    // Constructor vacío
    public User() {}

    // Constructor con parámetros
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

