package com.gestor.tasks;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private GestorTareas gestor;
    private DefaultListModel<String> modeloTareas;
    private JList<String> listaTareas;
    private List<Tarea> tareas;
    private JButton btnCompletar;
    private JButton btnEliminar;

    public VentanaPrincipal(GestorTareas gestor) {
        this.gestor = gestor;
        setTitle("Gestor de Tareas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 480);
        setLocationRelativeTo(null);

        modeloTareas = new DefaultListModel<>();
        listaTareas = new JList<>(modeloTareas);
        listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnNuevaTarea = new JButton("Nueva Tarea");
        btnCompletar = new JButton("Completar");
        btnEliminar = new JButton("Eliminar");
        JButton btnEstadisticas = new JButton("Estadísticas");

        btnNuevaTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearTareaDesdeDialogo();
            }
        });

        btnCompletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completarTarea();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTarea();
            }
        });

        btnEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEstadisticas();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnNuevaTarea);
        panelBotones.add(btnCompletar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEstadisticas);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelBotones, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(listaTareas), BorderLayout.CENTER);

        listaTareas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                actualizarEstadoBotones();
            }
        });

        setVisible(true);
        cargarTareas();
    }

    private void actualizarEstadoBotones() {
        boolean haySeleccion = listaTareas.getSelectedIndex() != -1;
        btnCompletar.setEnabled(haySeleccion);
        btnEliminar.setEnabled(haySeleccion);
    }

    private void crearTareaDesdeDialogo() {
        String titulo = JOptionPane.showInputDialog(this, "Título de la tarea:");
        if (titulo == null) {
            return;
        }
        String descripcion = JOptionPane.showInputDialog(this, "Descripción:", "Descripción");
        if (descripcion == null) {
            descripcion = "";
        }
        LocalDate fechaLimite = leerFechaLimite();
        Prioridad prioridad = leerPrioridad();
        if (prioridad == null) {
            return;
        }

        try {
            Tarea tarea = gestor.crearTarea(titulo, descripcion, fechaLimite, prioridad);
            JOptionPane.showMessageDialog(this, "Tarea creada correctamente con ID: " + tarea.getId());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error al crear la tarea: " + e.getMessage());
            return;
        }
        cargarTareas();
    }

    private LocalDate leerFechaLimite() {
        String entrada = JOptionPane.showInputDialog(this, "Fecha límite (formato yyyy-MM-dd):");
        if (entrada == null || entrada.trim().isEmpty()) {
            return LocalDate.now();
        }
        try {
            return LocalDate.parse(entrada.trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha no válido. Se usará la fecha actual.");
            return LocalDate.now();
        }
    }

    private Prioridad leerPrioridad() {
        Object seleccion = JOptionPane.showInputDialog(
                this,
                "Prioridad:",
                "Selecciona una prioridad",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"BAJA", "MEDIA", "ALTA"},
                "MEDIA"
        );
        if (seleccion == null) {
            return null;
        }
        String valor = seleccion.toString();
        if ("BAJA".equals(valor)) {
            return Prioridad.BAJA;
        }
        if ("MEDIA".equals(valor)) {
            return Prioridad.MEDIA;
        }
        if ("ALTA".equals(valor)) {
            return Prioridad.ALTA;
        }
        return Prioridad.MEDIA;
    }

    private void cargarTareas() {
        modeloTareas.clear();
        tareas = gestor.getTareas();
        for (Tarea tarea : tareas) {
            modeloTareas.addElement(tarea.toString());
        }
        actualizarEstadoBotones();
    }

    private Tarea obtenerTareaSeleccionada() {
        int indice = listaTareas.getSelectedIndex();
        if (indice < 0 || indice >= tareas.size()) {
            return null;
        }
        return tareas.get(indice);
    }

    private void completarTarea() {
        Tarea tarea = obtenerTareaSeleccionada();
        if (tarea == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea de la lista.");
            return;
        }
        Tarea actualizada = gestor.marcarCompletada(tarea.getId());
        if (actualizada != null) {
            JOptionPane.showMessageDialog(this, "Tarea marcada como COMPLETADA.");
            cargarTareas();
        }
    }

    private void eliminarTarea() {
        Tarea tarea = obtenerTareaSeleccionada();
        if (tarea == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea de la lista.");
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar esta tarea?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );
        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminada = gestor.eliminarTarea(tarea.getId());
            if (eliminada) {
                JOptionPane.showMessageDialog(this, "Tarea eliminada.");
                cargarTareas();
            }
        }
    }

    private void mostrarEstadisticas() {
        String mensaje = "Total de tareas: " + gestor.getTotalTareas()
                + "\nPendientes: " + gestor.getTareasPendientes()
                + "\nEn progreso: " + gestor.getTareasEnProgreso()
                + "\nCompletadas: " + gestor.getTareasCompletadas();
        JOptionPane.showMessageDialog(this, mensaje, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }
}
