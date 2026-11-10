package com.gestor.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Contiene la lógica de gestión de las tareas:
 * creación, búsqueda, modificación, eliminación,
 * filtrado y estadísticas.
 * Se encarga de coordinar la persistencia a través del repositorio.
 */
public class GestorTareas {

    private ArrayList<Tarea> tareas;
    private int ultimoId;
    private RepositorioTareas repositorio;

    public GestorTareas(RepositorioTareas repositorio) {
        this.tareas = new ArrayList<>();
        this.ultimoId = 1;
        this.repositorio = repositorio;
        this.tareas.addAll(repositorio.obtenerTareas());
    }

    public Tarea crearTarea(String titulo, String descripcion, LocalDate fechaLimite, Prioridad prioridad) {
        Tarea nuevaTarea = new Tarea(generarId(), titulo, descripcion, LocalDate.now(), fechaLimite, Estado.PENDIENTE, prioridad);
        Tarea tareaGuardada = repositorio.guardar(nuevaTarea);
        tareas.add(tareaGuardada);
        return tareaGuardada;
    }

    private int generarId() {
        int id = ultimoId;
        ultimoId++;
        return id;
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
        Tarea tarea = obtenerTareaPorId(id);
        if (tarea == null) {
            return false;
        }
        repositorio.eliminar(tarea);
        return tareas.remove(tarea);
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
        repositorio.actualizar(tarea);
        return tarea;
    }

    public Tarea marcarCompletada(int id) {
        Tarea tarea = obtenerTareaPorId(id);
        if (tarea == null) {
            return null;
        }
        tarea.setEstado(Estado.COMPLETADA);
        repositorio.actualizar(tarea);
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
