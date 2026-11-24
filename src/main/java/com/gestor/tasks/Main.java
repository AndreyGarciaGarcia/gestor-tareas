package com.gestor.tasks;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        GestorTareas gestor;
        try {
            MySQLRepositorio repositorio = new MySQLRepositorio();
            gestor = new GestorTareas(repositorio);
        } catch (Exception e) {
            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
            return;
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal(gestor).setVisible(true);
            }
        });
    }
}
