package com.gestor.tasks;

import java.util.List;

public interface RepositorioTareas {

    List<Tarea> obtenerTareas();

    Tarea obtenerTareaPorId(int id);

    Tarea guardar(Tarea tarea);

    void actualizar(Tarea tarea);

    void eliminar(Tarea tarea);
}
