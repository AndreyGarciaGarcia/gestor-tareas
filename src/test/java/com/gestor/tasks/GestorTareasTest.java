package com.gestor.tasks;

import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class GestorTareasTest {

    private RepositorioTareas crearRepositorioEnMemoria() {
        return new RepositorioTareas() {
            private final ArrayList<Tarea> tareas = new ArrayList<>();

            @Override
            public List<Tarea> obtenerTareas() {
                return new ArrayList<>(tareas);
            }

            @Override
            public Tarea obtenerTareaPorId(int id) {
                for (Tarea tarea : tareas) {
                    if (tarea.getId() == id) {
                        return tarea;
                    }
                }
                return null;
            }

            @Override
            public Tarea guardar(Tarea tarea) {
                tareas.add(tarea);
                return tarea;
            }

            @Override
            public void actualizar(Tarea tarea) {
                // No requiere acción en el repositorio en memoria
            }

            @Override
            public void eliminar(Tarea tarea) {
                tareas.remove(tarea);
            }
        };
    }

    @Test
    public void crearTareaAsignaIdAutomatico() {
        GestorTareas gestor = new GestorTareas(crearRepositorioEnMemoria());
        Tarea tarea = gestor.crearTarea("Comprar leche", "Ir al supermercado", LocalDate.now().plusDays(2), Prioridad.MEDIA);

        assertEquals(1, tarea.getId());
    }

    @Test
    public void eliminarTareaExistenteDevuelveTrue() {
        GestorTareas gestor = new GestorTareas(crearRepositorioEnMemoria());
        Tarea tarea = gestor.crearTarea("Tarea 1", "Descripción", LocalDate.now().plusDays(2), Prioridad.BAJA);

        assertTrue(gestor.eliminarTarea(tarea.getId()));
    }

    @Test
    public void marcarTareaComoCompletadaCambiaEstado() {
        GestorTareas gestor = new GestorTareas(crearRepositorioEnMemoria());
        Tarea tarea = gestor.crearTarea("Tarea 2", "Descripción", LocalDate.now().plusDays(2), Prioridad.BAJA);

        Tarea completada = gestor.marcarCompletada(tarea.getId());

        assertEquals(Estado.COMPLETADA, completada.getEstado());
    }

    @Test
    public void getTareasDevuelveCopiaDeLaLista() {
        GestorTareas gestor = new GestorTareas(crearRepositorioEnMemoria());
        gestor.crearTarea("Tarea 3", "Descripción", LocalDate.now().plusDays(2), Prioridad.ALTA);

        List<Tarea> tareas = gestor.getTareas();

        assertEquals(1, tareas.size());
    }
}
