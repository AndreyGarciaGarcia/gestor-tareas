package com.gestor.tasks;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class TareaTest {

    @Test
    public void tareaSeCreaCorrectamente() {
        Tarea tarea = new Tarea(1, "Estudiar Java", "Repasar Programación Orientada a Objetos", LocalDate.now(), LocalDate.of(2026, 11, 20), Estado.PENDIENTE, Prioridad.ALTA);

        assertEquals(1, tarea.getId());
        assertEquals("Estudiar Java", tarea.getTitulo());
        assertEquals("Repasar Programación Orientada a Objetos", tarea.getDescripcion());
        assertEquals(Estado.PENDIENTE, tarea.getEstado());
        assertEquals(Prioridad.ALTA, tarea.getPrioridad());
    }

    @Test(expected = IllegalArgumentException.class)
    public void tareaConTituloVacioNoSePuedeCrear() {
        new Tarea(2, "   ", "Descripción", LocalDate.now(), LocalDate.now().plusDays(1), Estado.PENDIENTE, Prioridad.BAJA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tareaConFechaLimiteAnteriorALaCreacionNoSePuedeCrear() {
        new Tarea(3, "Tarea inválida", "Descripción", LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 9), Estado.PENDIENTE, Prioridad.BAJA);
    }
}
