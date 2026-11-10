package com.gestor.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Gestiona la interacción con el usuario: muestra el menú,
 * lee las opciones y muestra los resultados devueltos por el GestorTareas.
 */
public class Menu {

    private static final int OPCION_SALIR = 0;

    private Scanner scanner;
    private GestorTareas gestor;

    public Menu() {
        scanner = new Scanner(System.in);
        gestor = new GestorTareas();
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Introduce una opción: ");

            switch (opcion) {
                case 1:
                    crearTarea();
                    break;
                case 2:
                    mostrarTareas();
                    break;
                case 3:
                    buscarTareaPorId();
                    break;
                case 4:
                    buscarTareasPorTitulo();
                    break;
                case 5:
                    modificarTarea();
                    break;
                case 6:
                    eliminarTarea();
                    break;
                case 7:
                    marcarTareaCompletada();
                    break;
                case 8:
                    filtrarPorEstado();
                    break;
                case 9:
                    filtrarPorPrioridad();
                    break;
                case 10:
                    mostrarEstadisticas();
                    break;
                case OPCION_SALIR:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Introduce un número del menú.");
                    break;
            }
        } while (opcion != OPCION_SALIR);
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("===== GESTOR DE TAREAS =====");
        System.out.println("1. Crear tarea");
        System.out.println("2. Ver todas las tareas");
        System.out.println("3. Buscar tarea por ID");
        System.out.println("4. Buscar tareas por título");
        System.out.println("5. Modificar tarea");
        System.out.println("6. Eliminar tarea");
        System.out.println("7. Marcar tarea como completada");
        System.out.println("8. Filtrar por estado");
        System.out.println("9. Filtrar por prioridad");
        System.out.println("10. Mostrar estadísticas");
        System.out.println("0. Salir");
    }

    private int leerEntero(String mensaje) {
        int valor = -1;
        boolean esValido = false;
        while (!esValido) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                esValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Introduce un número.");
            }
        }
        return valor;
    }

    private LocalDate leerFecha() {
        System.out.print("Introduce la fecha límite (formato yyyy-MM-dd): ");
        String entrada = scanner.nextLine().trim();
        try {
            return LocalDate.parse(entrada);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha no válido. Se usará la fecha actual.");
            return LocalDate.now();
        }
    }

    private Prioridad leerPrioridad() {
        int prioridadIntroducida = leerEntero("Introduce la prioridad (1=BAJA, 2=MEDIA, 3=ALTA): ");
        Prioridad prioridadSeleccionada;
        if (prioridadIntroducida == 1) {
            prioridadSeleccionada = Prioridad.BAJA;
        } else if (prioridadIntroducida == 2) {
            prioridadSeleccionada = Prioridad.MEDIA;
        } else if (prioridadIntroducida == 3) {
            prioridadSeleccionada = Prioridad.ALTA;
        } else {
            System.out.println("Prioridad no válida. Se asignará BAJA.");
            prioridadSeleccionada = Prioridad.BAJA;
        }
        return prioridadSeleccionada;
    }

    private void crearTarea() {
        System.out.print("Introduce el título de la tarea: ");
        String titulo = scanner.nextLine().trim();
        System.out.print("Introduce la descripción: ");
        String descripcion = scanner.nextLine().trim();
        LocalDate fechaLimite = leerFecha();
        Prioridad prioridad = leerPrioridad();

        try {
            Tarea tareaCreada = gestor.crearTarea(titulo, descripcion, fechaLimite, prioridad);
            System.out.println("Tarea creada correctamente con ID: " + tareaCreada.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear la tarea: " + e.getMessage());
        }
    }

    private void mostrarTareas() {
        List<Tarea> todas = gestor.getTareas();
        if (todas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        for (Tarea tarea : todas) {
            System.out.println(tarea);
        }
    }

    private void buscarTareaPorId() {
        int id = leerEntero("Introduce el ID de la tarea a buscar: ");
        Tarea tarea = gestor.obtenerTareaPorId(id);
        if (tarea == null) {
            System.out.println("No se ha encontrado ninguna tarea con ID " + id + ".");
        } else {
            System.out.println(tarea);
        }
    }

    private void buscarTareasPorTitulo() {
        System.out.print("Introduce el título (puedes escribir solo una parte): ");
        String titulo = scanner.nextLine().trim();
        List<Tarea> resultados = gestor.buscarPorTitulo(titulo);
        if (resultados.isEmpty()) {
            System.out.println("No se han encontrado tareas.");
            return;
        }
        for (Tarea tarea : resultados) {
            System.out.println(tarea);
        }
    }

    private void modificarTarea() {
        int id = leerEntero("Introduce el ID de la tarea a modificar: ");
        Tarea tarea = gestor.obtenerTareaPorId(id);
        if (tarea == null) {
            System.out.println("No se ha encontrado ninguna tarea con ID " + id + ".");
            return;
        }
        System.out.print("Introduce el nuevo título: ");
        String titulo = scanner.nextLine().trim();
        System.out.print("Introduce la nueva descripción: ");
        String descripcion = scanner.nextLine().trim();
        LocalDate fechaLimite = leerFecha();
        Prioridad prioridad = leerPrioridad();

        Tarea tareaActualizada = gestor.actualizarTarea(id, titulo, descripcion, fechaLimite, prioridad);
        if (tareaActualizada != null) {
            System.out.println("Tarea con ID " + id + " actualizada correctamente.");
        }
    }

    private void eliminarTarea() {
        int id = leerEntero("Introduce el ID de la tarea a eliminar: ");
        boolean eliminada = gestor.eliminarTarea(id);
        if (eliminada) {
            System.out.println("Tarea con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("No se ha encontrado ninguna tarea con ID " + id + ".");
        }
    }

    private void marcarTareaCompletada() {
        int id = leerEntero("Introduce el ID de la tarea a marcar como completada: ");
        Tarea tarea = gestor.marcarCompletada(id);
        if (tarea != null) {
            System.out.println("Tarea con ID " + id + " marcada como COMPLETADA.");
        } else {
            System.out.println("No se ha encontrado ninguna tarea con ID " + id + ".");
        }
    }

    private void filtrarPorEstado() {
        int estadoIntroducido = leerEntero("Introduce un estado (1=PENDIENTE, 2=EN_PROGRESO, 3=COMPLETADA): ");
        Estado estadoSeleccionado;
        if (estadoIntroducido == 1) {
            estadoSeleccionado = Estado.PENDIENTE;
        } else if (estadoIntroducido == 2) {
            estadoSeleccionado = Estado.EN_PROGRESO;
        } else if (estadoIntroducido == 3) {
            estadoSeleccionado = Estado.COMPLETADA;
        } else {
            System.out.println("Estado no válido.");
            return;
        }

        List<Tarea> resultados = gestor.filtrarPorEstado(estadoSeleccionado);
        if (resultados.isEmpty()) {
            System.out.println("No hay tareas con ese estado.");
            return;
        }
        for (Tarea tarea : resultados) {
            System.out.println(tarea);
        }
    }

    private void filtrarPorPrioridad() {
        int prioridadIntroducida = leerEntero("Introduce una prioridad (1=BAJA, 2=MEDIA, 3=ALTA): ");
        Prioridad prioridadSeleccionada;
        if (prioridadIntroducida == 1) {
            prioridadSeleccionada = Prioridad.BAJA;
        } else if (prioridadIntroducida == 2) {
            prioridadSeleccionada = Prioridad.MEDIA;
        } else if (prioridadIntroducida == 3) {
            prioridadSeleccionada = Prioridad.ALTA;
        } else {
            System.out.println("Prioridad no válida.");
            return;
        }

        List<Tarea> resultados = gestor.filtrarPorPrioridad(prioridadSeleccionada);
        if (resultados.isEmpty()) {
            System.out.println("No hay tareas con esa prioridad.");
            return;
        }
        for (Tarea tarea : resultados) {
            System.out.println(tarea);
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("----- ESTADÍSTICAS -----");
        System.out.println("Total de tareas: " + gestor.getTotalTareas());
        System.out.println("Pendientes: " + gestor.getTareasPendientes());
        System.out.println("En progreso: " + gestor.getTareasEnProgreso());
        System.out.println("Completadas: " + gestor.getTareasCompletadas());
    }
}
