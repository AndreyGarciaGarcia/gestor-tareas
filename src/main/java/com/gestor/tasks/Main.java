package com.gestor.tasks;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación (interfaz gráfica con Swing).
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}
