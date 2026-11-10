# Gestor de Tareas (Task Manager)

Aplicación de gestión de tareas con interfaz gráfica desarrollada en **Java** utilizando **Swing**.

Permite a los usuarios organizar sus actividades, consultar su estado y administrar sus tareas a través de una ventana gráfica.

## Funcionalidades

- ✅ Crear tareas con:
  - ID único.
  - Título.
  - Descripción.
  - Fecha de creación.
  - Fecha límite.
  - Estado (pendiente, en progreso o completada).
  - Prioridad (baja, media o alta).
- ✅ Mostrar todas las tareas en una lista.
- ✅ Marcar una tarea como completada.
- ✅ Eliminar una tarea.
- ✅ Mostrar estadísticas: total, pendientes, en progreso y completadas.

## Tecnologías utilizadas

- Java.
- Swing (interfaz gráfica).
- Programación Orientada a Objetos (POO).
- Colecciones de Java (`ArrayList`).
- Git / GitHub.

No se utiliza ningún framework (como Spring Boot) ni base de datos en esta primera versión.

## Arquitectura

El proyecto sigue una separación de responsabilidades:

- **`Main`**: punto de entrada de la aplicación (crea la ventana con `SwingUtilities`).
- **`VentanaPrincipal`**: interfaz gráfica (Swing) que se comunica con el controlador de la ventana.
- **`GestorTareas`**: contiene la lógica de negocio y la gestión de las tareas.
- **`Tarea`**: representa una tarea (modelo/concepto) con sus atributos y validaciones.
- **`Estado`**: enumeración con los estados posibles de una tarea.
- **`Prioridad`**: enumeración con las prioridades posibles.

Flujo general: `Main` → `VentanaPrincipal` → `GestorTareas` → `Tarea`.

## Estructura de carpetas

```
src/
└── main/
    └── java/
        └── com/
            └── gestor/
                └── tasks/
                    ├── Main.java
                    ├── VentanaPrincipal.java
                    ├── GestorTareas.java
                    ├── Tarea.java
                    ├── Estado.java
                    └── Prioridad.java
```

## Cómo ejecutarlo

### Requisitos

- JDK 8 o superior (Swing está incluido en el JDK).

### Compilar

```bash
javac -d out $(find src -name "*.java")
```

### Ejecutar

```bash
java -cp out com.gestor.tasks.Main
```

## Estado del proyecto

- [x] Estructura base y arquitectura.
- [x] Modelo de tareas con validaciones.
- [x] Lógica de gestión (creación, completado, eliminación, estadísticas).
- [x] Interfaz gráfica con Swing.
- [ ] Persistencia en base de datos.
- [ ] Añadir tests unitarios (JUnit).

## Próximos pasos

- Añadir persistencia (archivo o base de datos).
- Escribir tests unitarios con JUnit.

## Autor

Proyecto desarrollado como portfolio de Java Junior.
