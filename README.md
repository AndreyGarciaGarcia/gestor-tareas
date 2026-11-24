# Gestor de tareas

Una aplicación de escritorio sencilla para apuntar tareas y llevar un seguimiento de lo que queda por hacer. La hice como proyecto de práctica con Java: la interfaz está hecha con Swing y las tareas se guardan en MySQL.

## Qué puedes hacer

- Crear tareas con título, descripción, fecha límite y prioridad.
- Ver las tareas guardadas, marcarlas como completadas o eliminarlas.
- Consultar un recuento de tareas pendientes, en curso y completadas.

Cada tarea recibe su fecha de creación al guardarse y empieza en estado pendiente. La interfaz actual permite completar tareas, pero todavía no cambiar manualmente su estado a «en progreso».

## Cómo está organizado

El código está en `src/main/java/com/gestor/tasks` y las pruebas de modelo y lógica, en `src/test/java/com/gestor/tasks`.

`VentanaPrincipal` se ocupa de la ventana y los diálogos. `GestorTareas` aplica las operaciones sobre las tareas, y `MySQLRepositorio` las guarda y recupera mediante JDBC. La interfaz `RepositorioTareas` mantiene separada la lógica de la base de datos.

## Requisitos y configuración

- JDK 8 o superior.
- MySQL en ejecución y una base de datos llamada `gestor_tareas`.
- El conector MySQL incluido en `lib`.

La conexión está configurada en `MySQLRepositorio`: servidor local en el puerto `3306`, usuario `root` y contraseña vacía. Si tu instalación usa otros datos, cambia esas constantes antes de ejecutar la aplicación. La tabla `tareas` se crea al iniciar si todavía no existe.

## Compilar y ejecutar

Desde la raíz del proyecto, en Windows:

```bat
javac -d out src\main\java\com\gestor\tasks\*.java
java -cp "out;lib\mysql-connector-j-8.0.33.jar" com.gestor.tasks.Main
```

En Linux o macOS:

```sh
mkdir -p out
javac -d out src/main/java/com/gestor/tasks/*.java
java -cp "out:lib/mysql-connector-j-8.0.33.jar" com.gestor.tasks.Main
```

## Pruebas

Las pruebas usan JUnit 4. Desde Windows:

```bat
javac -cp "lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" -d out_test src\main\java\com\gestor\tasks\*.java src\test\java\com\gestor\tasks\*.java
java -cp "out_test;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore com.gestor.tasks.TareaTest com.gestor.tasks.GestorTareasTest
```
