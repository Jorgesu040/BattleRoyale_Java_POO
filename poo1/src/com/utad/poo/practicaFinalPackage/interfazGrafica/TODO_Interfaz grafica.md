# TODO Interfaz gráfica

- **Completar la implementación en `MapGenerator.java`:**
  - **Método `renderGrid(Graphics2D g2d)`:** El bucle que dibuja la cuadrícula del mapa está incompleto (`{...}`). Debes implementar la lógica para generar y dibujar los tiles en sus posiciones correspondientes, teniendo en cuenta el desplazamiento y el espaciado.
  - **Método `generateRandomTileType()`:** El bucle `while` debe completarse para asegurarse de que los tipos de tiles generados aleatoriamente no incluyan ciertos tipos (por ejemplo, `TILE_SPAWN`, `TILE_SPAWN_AI`, `TILE_TRAP_SET`, `TILE_TRAP_IDLE`).
  - **Método `generateRandomTile(...)`:** El código para asignar tipos de tiles para jugadores, bandidos y trampas está comentado. Necesitas descomentar y ajustar este código para que funcione correctamente según los contadores y límites establecidos.
  - **Método `handleTileClick(MouseEvent click)`:** Actualmente está vacío (`{...}`). Debes implementar la detección de clics en los tiles y definir las acciones que ocurren al hacer clic en un tile.

- **Actualizar la clase `Tile.java`:**
  - **Gestión de `objectoOcupado`:** Implementar lógica para manejar los objetos que ocupan un tile, permitiendo asignar y liberar estos objetos.
  - **Método `toString()`:** Mejorar este método para proporcionar información más detallada, lo que ayudará en la depuración y comprensión del estado de cada tile.

- **Optimizar y validar datos:**
  - **Validación en `MapGenerator`:** Asegurar que los valores de entrada como el tamaño del mapa, cantidad de trampas, jugadores y bandidos sean válidos y estén dentro de límites razonables.
  - **Manejo de excepciones:** Añadir manejo de excepciones para prevenir y manejar posibles errores durante la generación y renderizado del mapa.

- **Desarrollar la interfaz de usuario en

ventanaTesting.java

:**

- Añadir elementos interactivos para la entrada del usuario, como botones para iniciar o reiniciar el juego.
- Implementar retroalimentación visual en los tiles (e.g., resaltar al pasar el ratón o al seleccionar).

- **Implementar mecánicas de juego:**
  - **Movimiento y acciones:** Permitir que los jugadores y los enemigos se muevan y realicen acciones en el mapa.
  - **Interacciones:** Definir cómo los personajes interactúan con diferentes tipos de tiles (e.g., recoger loot, activar trampas).
  - **Combate:** Implementar las mecánicas de combate entre jugadores y bandidos controlados por IA.

- **Documentar y estructurar el proyecto:**
  - **Crear

README.md

:** Detallar la descripción del proyecto, instrucciones de ejecución y características.

- **Crear `TODO.md`:** Listar las tareas pendientes y organizar el desarrollo futuro.
- **Comentarios y documentación interna:** Añadir comentarios claros en el código para facilitar su mantenimiento y comprensión.

- **Escribir pruebas unitarias:**
  - **Clases importantes:** Crear pruebas para las clases `Tile` y `MapGenerator` para asegurar que funcionan según lo esperado.
  - **Cobertura de casos:** Incluir pruebas para la generación del mapa, la asignación de tipos de tiles y las interacciones con el usuario.

- **Mejoras adicionales:**
  - **Optimización gráfica:** Mejorar el rendimiento del renderizado si es necesario, especialmente si se incrementa el tamaño del mapa.
  - **Configuración dinámica:** Permitir que ciertos parámetros (como el número de jugadores o tamaño del mapa) puedan ser configurados dinámicamente por el usuario.

Espero que esta lista te ayude a identificar las áreas que requieren atención en tu proyecto y a planificar los siguientes pasos de desarrollo.
