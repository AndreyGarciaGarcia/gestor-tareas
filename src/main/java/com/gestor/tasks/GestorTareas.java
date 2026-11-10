package com.gestor.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Contiene la lógica de gestión de las tareas:
 * creación, búsqueda, modificación, eliminación,
 * filtrado y estadísticas.
 */
public class GestorTareas {

    private ArrayList<Tarea> tareas;
    private int ultimoId;

    public GestorTareas() {
        this.tareas = new ArrayList<>();
        this.ultimoId = 1;
    }

    public Tarea crearTarea(String titulo, String descripcion, LocalDate fechaLimite, Prioridad prioridad) {
        Tarea nuevaTarea = new Tarea(generarId(), titulo, descripcion, LocalDate.now(), fechaLimite, Estado.PENDIENTE, prioridad);
        tareas.add(nuevaTarea);
        return nuevaTarea;
    }

    private int generarId() {
        int id = ultimoId;
        ultimoId++;
        while (existeId(id)) {
            id = ultimoId;
            ultimoId++;
        }
        return id;
    }

    private boolean existeId(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas);
    }

    public Tarea obtenerTareaPorId(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }

    public List<Tarea> buscarPorTitulo(String tituloBuscado) {
        List<Tarea> resultados = new ArrayList<>();
        String busqueda = tituloBuscado.toLowerCase();
        for (Tarea tarea : tareas) {
            if (tarea.getTitulo().toLowerCase().contains(busqueda)) {
                resultados.add(tarea);
            }
        }
        return resultados;
    }

    public boolean eliminarTarea(int id) {
        return tareas.removeIf(tarea -> tarea.getId() == id);
    }

    public Tarea actualizarTarea(int id, String titulo, String descripcion, LocalDate fechaLimite, Prioridad prioridad) {
        Tarea tarea = obtenerTareaPorId(id);
        if (tarea == null) {
            return null;
        }
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setFechaLimite(fechaLimite);
        tarea.setPrioridad(prioridad);
        return tarea;
    }

    public Tarea marcarCompletada(int id) {
        Tarea tarea = obtenerTareaPorId(id);
        if (tarea != null) {
            tarea.setEstado(Estado.COMPLETADA);
        }
        return tarea;
    }

    public List<Tarea> filtrarPorEstado(Estado estado) {
        List<Tarea> resultados = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado().equals(estado)) {
                resultados.add(tarea);
            }
        }
        return resultados;
    }

    public List<Tarea> filtrarPorPrioridad(Prioridad prioridad) {
        List<Tarea> resultados = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getPrioridad().equals(prioridad)) {
                resultados.add(tarea);
            }
        }
        return resultados;
    }

    public int getTotalTareas() {
        return tareas.size();
    }

    public int getTareasPendientes() {
        return contarPorEstado(Estado.PENDIENTE);
    }

    public int getTareasEnProgreso() {
        return contarPorEstado(Estado.EN_PROGRESO);
    }

    public int getTareasCompletadas() {
        return contarPorEstado(Estado.COMPLETADA);
    }

    private int contarPorEstado(Estado estado) {
        int contador = 0;
        for (Tarea tarea : tareas) {
            if (tarea.getEstado().equals(estado)) {
                contador++;
            }
        }
        return contador;
    }
}
