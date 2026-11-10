# Gestor de Tareas (Task Manager)

Aplicación de gestión de tareas con interfaz gráfica desarrollada en **Java** utilizando **Swing**, con **persistencia en base de datos MySQL** mediante **JDBC**.

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
- ✅ Persistencia en base de datos **MySQL** (guardado y carga automática).

## Tecnologías utilizadas

- Java.
- Swing (interfaz gráfica).
- JDBC + MySQL Connector/J (persistencia).
- Programación Orientada a Objetos (POO).
- Colecciones de Java (`ArrayList`).
- JUnit (tests).
- Git / GitHub.

No se utiliza ningún framework (como Spring Boot) en esta versión.

## Arquitectura

El proyecto sigue una separación de responsabilidades:

- **`Main`**: punto de entrada de la aplicación (crea el repositorio, la instancia de gestor y arranca la ventana).
- **`VentanaPrincipal`**: interfaz gráfica (Swing) que se comunica con el gestor.
- **`GestorTareas`**: contiene la lógica de negocio y la gestión de las tareas.
- **`Tarea`**: representa una tarea (modelo/concepto) con sus atributos y validaciones.
- **`RepositorioTareas`**: interfaz de acceso a datos.
- **`MySQLRepositorio`**: implementación del repositorio con JDBC y MySQL.
- **`Estado`**: enumeración con los estados posibles de una tarea.
- **`Prioridad`**: enumeración con las prioridades posibles.

Flujo general: `Main` → `VentanaPrincipal` → `GestorTareas` → `RepositorioTareas` → `Tarea`.

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
└── lib/ (dependencias: JUnit, MySQL Connector/J)
```

## Cómo ejecutarlo

### Requisitos

- JDK 8 o superior.
- Servidor **MySQL** en ejecución.
- Base de datos `gestor_tareas` creada (la tabla `tareas` se crea automáticamente).

### Configuración de la base de datos

En `MySQLRepositorio` se define:

- URL: `jdbc:mysql://localhost:3306/gestor_tareas?useSSL=false&serverTimezone=UTC`
- Usuario: `root`
- Contraseña: (vacía por defecto)

### Compilar

```bash
javac -d out $(find src/main -name "*.java")
```

### Ejecutar

```bash
java -cp out;lib/mysql-connector-j-8.0.33.jar com.gestor.tasks.Main
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
- [x] Persistencia en base de datos MySQL (JDBC).
- [x] Tests unitarios con JUnit.

## Próximos pasos

- Añadir framework (Spring Boot) para la capa de persistencia.
- Ampliar tests (repositorio, integración con BD).

## Autor

Proyecto desarrollado como portfolio de Java Junior.
