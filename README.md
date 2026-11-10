# Gestor de Tareas (Task Manager)

Aplicación de gestión de tareas con interfaz gráfica desarrollada en **Java** utilizando **Swing**.

Permite a los usuarios organizar sus actividades, consultar su estado y administrar sus tareas a través de una ventana gráfica, con persistencia en archivo.

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
- ✅ Persistencia en archivo (`tareas.txt`) con guardado y carga automática.

## Tecnologías utilizadas

- Java.
- Swing (interfaz gráfica).
- Programación Orientada a Objetos (POO).
- Colecciones de Java (`ArrayList`).
- JUnit (tests).
- Git / GitHub.

No se utiliza ningún framework (como Spring Boot) ni base de datos en esta primera versión.

## Arquitectura

El proyecto sigue una separación de responsabilidades:

- **`Main`**: punto de entrada de la aplicación (crea el gestor, carga las tareas y arranca la ventana).
- **`VentanaPrincipal`**: interfaz gráfica (Swing) que se comunica con el gestor.
- **`GestorTareas`**: contiene la lógica de negocio y la gestión de las tareas.
- **`Tarea`**: representa una tarea (modelo/concepto) con sus atributos y validaciones.
- **`Estado`**: enumeración con los estados posibles de una tarea.
- **`Prioridad`**: enumeración con las prioridades posibles.

Flujo general: `Main` → `VentanaPrincipal` → `GestorTareas` → `Tarea`.

## Estructura de carpetas

```
src/
├── main/
│   └── java/
│       └── com/
│           └── gestor/
│               └── tasks/
├── test/
│   └── java/
│       └── com/
│           └── gestor/
│               └── tasks/
└── lib/ (dependencias: JUnit)
```

## Cómo ejecutarlo

### Requisitos

- JDK 8 o superior (Swing y JUnit están incluidos/descargables).

### Compilar

```bash
javac -d out $(find src/main -name "*.java")
```

### Ejecutar

```bash
java -cp out com.gestor.tasks.Main
```

### Ejecutar los tests

```bash
javac -cp "lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" -d out_test $(find src -name "*.java")
java -cp "out_test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore com.gestor.tasks.TareaTest com.gestor.tasks.GestorTareasTest
```

## Estado del proyecto

- [x] Estructura base y arquitectura.
- [x] Modelo de tareas con validaciones.
- [x] Lógica de gestión (creación, completado, eliminación, estadísticas).
- [x] Interfaz gráfica con Swing.
- [x] Persistencia en archivo.
- [x] Tests unitarios con JUnit.
- [ ] Base de datos / framework / interfaz avanzada.

## Próximos pasos

- Sustituir la persistencia en archivo por una base de datos.
- Añadir más tests (filtros, estadísticas, persistencia).
- Mejoras de la interfaz gráfica.

## Autor

Proyecto desarrollado como portfolio de Java Junior.
