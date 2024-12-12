# Lista de Tareas

## 1. Implementar conexión entre interfaz gráfica y lógica del juego

- Implementar la conexión entre los botones de la interfaz gráfica y las acciones/items de un personaje.

## 2. Implementar el movimiento del personaje por los tiles

## 3. Implementar la mecánica de cambio de equipamiento

- Permitir que los personajes cambien su `Escudo`.
- Asegurarse de que el nuevo equipamiento impacte correctamente las estadísticas del personaje.

## 4. Construir el flujo del juego

- Crear una clase `Partida` para controlar la progresión del juego.
- Gestionar la inicialización de personajes, distribución de turnos y condiciones de fin de juego.

## 5. Mejorar el manejo de errores y la validación de entradas

- Validar las entradas de los usuarios para prevenir datos inválidos.
- Agregar manejo de excepciones para errores imprevistos durante el juego.

## 6. Optimizar y refactorizar el código **IMPORTANTE: mi parte gráfica es un poco desastre**

- Revisar el código para consistencia en estilo y convenciones de nombres.
- Refactorizar segmentos de código redundantes o ineficientes.
- Asegurarse de que los comentarios estén actualizados y sean útiles.

## 7. Implementar un sistema de cargado desde un fichero

- Crear un sistema de cargado de ajustes de juego desde un archivo de configuración.
- Control de errores con Try-Catch.

## 8. Implementar un sistema de logs: **Punto 7 del pdf de blackboard**

- Una clase `Logs` que:
  - O cada `Personaje`, tenga una instancia de `Logs` que registre sus acciones.
  - O que la clase `Partida` tenga una instancia de `Logs` que registre las acciones de todos los personajes.
- Los logs deben ser guardados en un archivo de texto.
- Los logs deben guardar:
  - Acciones de movimiento.
  - Acciones de cada personaje en cada turno.
  - Al finalizar el turno un 'rundown' del estado (vida, defensa, ataque) de cada personaje.

