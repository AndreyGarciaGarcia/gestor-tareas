package com.gestor.tasks;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación (interfaz gráfica con Swing).
 */
public class Main {

    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        gestor.cargarDesdeArchivo("tareas.txt");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal(gestor).setVisible(true);
            }
        });
    }
}
