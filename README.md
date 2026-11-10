# Gestor de Tareas (Task Manager)

Aplicación de línea de comandos desarrollada en **Java** para la gestión y organización personal de tareas.

El proyecto permite a los usuarios crear, consultar, modificar, eliminar y filtrar tareas, así como visualizar estadísticas de su estado. Es una aplicación de escritorio/consola sin frameworks ni bases de datos, ideal para poner en práctica los fundamentos de la Programación Orientada a Objetos en Java.

## Funcionalidades

- ✅ Crear tareas con:
  - ID único.
  - Título.
  - Descripción.
  - Fecha de creación.
  - Fecha límite.
  - Estado (pendiente, en progreso o completada).
  - Prioridad (baja, media o alta).
- ✅ Mostrar todas las tareas.
- ✅ Buscar tareas por ID o por título.
- ✅ Modificar una tarea.
- ✅ Eliminar una tarea.
- ✅ Marcar una tarea como completada.
- ✅ Filtrar tareas por estado y por prioridad.
- ✅ Mostrar estadísticas: total, pendientes, en progreso y completadas.
- ✅ Validación de datos de entrada para evitar errores.

## Tecnologías utilizadas

- Java.
- Programación Orientada a Objetos (POO).
- Colecciones de Java (`ArrayList`).
- Git / GitHub.

No se utiliza ningún framework (como Spring Boot) ni base de datos en esta primera versión, con el objetivo de consolidar los fundamentos de Java.

## Arquitectura

El proyecto sigue una separación de responsabilidades:

- **`Main`**: punto de entrada de la aplicación.
- **`Menu`**: gestiona la interacción con el usuario (entrada/salida).
- **`GestorTareas`**: contiene la lógica de negocio y la gestión de las tareas.
- **`Tarea`**: representa una tarea (modelo/concepto) con sus atributos y validaciones.
- **`Estado`**: enumeración con los estados posibles de una tarea.
- **`Prioridad`**: enumeración con las prioridades posibles.

Flujo general: `Main` → `Menu` → `GestorTareas` → `Tarea`.

## Estructura de carpetas

```
src/
└── main/
    └── java/
        └── com/
            └── gestor/
                └── tasks/
                    ├── Main.java
                    ├── Menu.java
                    ├── GestorTareas.java
                    ├── Tarea.java
                    ├── Estado.java
                    └── Prioridad.java
```

## Cómo ejecutarlo

### Requisitos

- JDK 8 o superior (se utiliza `LocalDate`).

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
- [x] Lógica de gestión (CRUD, filtros, estadísticas).
- [x] Interfaz de línea de comandos.
- [ ] Persistencia en base de datos.
- [ ] Añadir tests unitarios (JUnit).
- [ ] Interfaz gráfica de usuario (GUI).

## Próximos pasos

- Añadir persistencia (archivo o base de datos).
- Escribir tests unitarios con JUnit.
- Crear una interfaz gráfica (Swing/JavaFX).
- Refactorizar y mejorar la separación de capas.

## Autor

Proyecto desarrollado como portfolio de Java Junior.
