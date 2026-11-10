package com.gestor.tasks;

import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

public class GestorTareasTest {

    @Test
    public void crearTareaAsignaIdAutomatico() {
        GestorTareas gestor = new GestorTareas();
        Tarea tarea = gestor.crearTarea("Comprar leche", "Ir al supermercado", LocalDate.now().plusDays(2), Prioridad.MEDIA);

        assertEquals(1, tarea.getId());
    }

    @Test
    public void eliminarTareaExistenteDevuelveTrue() {
        GestorTareas gestor = new GestorTareas();
        Tarea tarea = gestor.crearTarea("Tarea 1", "Descripción", LocalDate.now().plusDays(2), Prioridad.BAJA);

        assertTrue(gestor.eliminarTarea(tarea.getId()));
    }

    @Test
    public void marcarTareaComoCompletadaCambiaEstado() {
        GestorTareas gestor = new GestorTareas();
        Tarea tarea = gestor.crearTarea("Tarea 2", "Descripción", LocalDate.now().plusDays(2), Prioridad.BAJA);

        Tarea completada = gestor.marcarCompletada(tarea.getId());

        assertEquals(Estado.COMPLETADA, completada.getEstado());
    }

    @Test
    public void getTareasDevuelveCopiaDeLaLista() {
        GestorTareas gestor = new GestorTareas();
        gestor.crearTarea("Tarea 3", "Descripción", LocalDate.now().plusDays(2), Prioridad.ALTA);

        List<Tarea> tareas = gestor.getTareas();

        assertEquals(1, tareas.size());
    }
}
