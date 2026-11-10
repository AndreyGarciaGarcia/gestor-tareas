package com.gestor.tasks;

import java.util.List;

/**
 * Contrato de acceso a datos para las tareas.
 * Permite que la lógica de negocio (GestorTareas) no dependa
 * directamente de una implementación concreta (MySQL).
 */
public interface RepositorioTareas {

    List<Tarea> obtenerTareas();

    Tarea obtenerTareaPorId(int id);

    Tarea guardar(Tarea tarea);

    void actualizar(Tarea tarea);

    void eliminar(Tarea tarea);
}
