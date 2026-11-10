package com.gestor.tasks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio de tareas usando JDBC y MySQL.
 */
public class MySQLRepositorio implements RepositorioTareas {

    private static final String URL = "jdbc:mysql://localhost:3306/gestor_tareas?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    private Connection conexion;

    public MySQLRepositorio() throws SQLException {
        conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        crearTabla();
    }

    private void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tareas ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "titulo VARCHAR(100) NOT NULL,"
                + "descripcion VARCHAR(255),"
                + "fecha_creacion DATE,"
                + "fecha_limite DATE,"
                + "estado VARCHAR(20),"
                + "prioridad VARCHAR(10))";
        try (Statement stmt = conexion.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public List<Tarea> obtenerTareas() {
        List<Tarea> tareas = new ArrayList<>();
        String sql = "SELECT * FROM tareas ORDER BY id";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tareas.add(convertirTarea(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las tareas de la base de datos", e);
        }
        return tareas;
    }

    @Override
    public Tarea obtenerTareaPorId(int id) {
        Tarea resultado = null;
        String sql = "SELECT * FROM tareas WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resultado = convertirTarea(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la tarea con ID " + id, e);
        }
        return resultado;
    }

    @Override
    public Tarea guardar(Tarea tarea) {
        String sql = "INSERT INTO tareas (titulo, descripcion, fecha_creacion, fecha_limite, estado, prioridad) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());
            ps.setDate(3, Date.valueOf(tarea.getFechaCreacion()));
            ps.setDate(4, tarea.getFechaLimite() != null ? Date.valueOf(tarea.getFechaLimite()) : null);
            ps.setString(5, tarea.getEstado().name());
            ps.setString(6, tarea.getPrioridad().name());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tarea.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la tarea en la base de datos", e);
        }
        return tarea;
    }

    @Override
    public void actualizar(Tarea tarea) {
        String sql = "UPDATE tareas SET titulo = ?, descripcion = ?, fecha_creacion = ?, fecha_limite = ?, estado = ?, prioridad = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());
            ps.setDate(3, Date.valueOf(tarea.getFechaCreacion()));
            ps.setDate(4, tarea.getFechaLimite() != null ? Date.valueOf(tarea.getFechaLimite()) : null);
            ps.setString(5, tarea.getEstado().name());
            ps.setString(6, tarea.getPrioridad().name());
            ps.setInt(7, tarea.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la tarea en la base de datos", e);
        }
    }

    @Override
    public void eliminar(Tarea tarea) {
        String sql = "DELETE FROM tareas WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, tarea.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la tarea de la base de datos", e);
        }
    }

    private Tarea convertirTarea(ResultSet rs) throws SQLException {
        Tarea tarea = new Tarea(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("descripcion"),
                rs.getDate("fecha_creacion") != null ? rs.getDate("fecha_creacion").toLocalDate() : null,
                rs.getDate("fecha_limite") != null ? rs.getDate("fecha_limite").toLocalDate() : null,
                Estado.valueOf(rs.getString("estado")),
                Prioridad.valueOf(rs.getString("prioridad"))
        );
        tarea.setId(rs.getInt("id"));
        return tarea;
    }
}
