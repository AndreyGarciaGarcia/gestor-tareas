package com.gestor.tasks;

import java.time.LocalDate;

public class Tarea {

    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private Estado estado;
    private Prioridad prioridad;

    public Tarea(int id, String titulo, String descripcion, LocalDate fechaCreacion, LocalDate fechaLimite, Estado estado, Prioridad prioridad) {
        this.id = id;
        setTitulo(titulo);
        this.descripcion = descripcion != null ? descripcion : "";
        this.fechaCreacion = fechaCreacion;
        setFechaLimite(fechaLimite);
        setEstado(estado);
        setPrioridad(prioridad);
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public Estado getEstado() {
        return estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        this.titulo = titulo.trim();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        if (fechaLimite != null && fechaCreacion != null && fechaLimite.isBefore(fechaCreacion)) {
            throw new IllegalArgumentException("La fecha límite no puede ser anterior a la fecha de creación");
        }
        this.fechaLimite = fechaLimite;
    }

    public void setEstado(Estado estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        this.estado = estado;
    }

    public void setPrioridad(Prioridad prioridad) {
        if (prioridad == null) {
            throw new IllegalArgumentException("La prioridad no puede ser nula");
        }
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Título: " + titulo
                + " | Descripción: " + descripcion
                + " | Fecha creación: " + fechaCreacion
                + " | Fecha límite: " + fechaLimite
                + " | Estado: " + estado
                + " | Prioridad: " + prioridad;
    }
}
