# Planificacion / ideas del proyecto

## **Fase de diseño básico**

1. **Diseño de clases**:
   - Crea una jerarquía con una clase base `Personaje` y subclases como:
     - `Guerrero`: Mayor ataque.
     - `Arquero`: Mayor alcance.
     - `Mago`: Habilidades especiales.
   - Diseña una clase base `Herramienta` y subclases:
     - `Arma`: Espada, arco.
     - `Defensa`: Escudo, armadura.
     - `Item`: Pociones, trampas.
         - Por cada enfrentamiento, un `Personaje` puede encontrar un `Item` (Probabilidad), que se clasisifcan en:
            - Pociones:
               1. De curacion
               2. De defensa
               3. De mayor ataque
            - Trampas: que al finalizar un turno se aplican sobre un `Personaje` (vivo) al azar; el comportamiento es el siguiente:
               - El `Personaje` elegido, tiene un 50% de caer en la trampa que aplica un efecto permante sobre dicho `Personaje`
                  1. Quitar vida
                  2. Reducir defensa
                  3. Reducir ataque

2. **Niveles de dificultad**:
   - Implementa un sistema que aumente los atributos de los personajes controlados por máquina en dificultades más altas, como más vida o daño.
   - Como se nos puede ir de las manos el tema de dificultad, yo lo haria con unos valores base (dificultad normal), aplicamos porcentajes sobre datos (por ej. el daño que recibe el jugador puede ser menor o encontrar mejores objetos)

3. **Sistema de turnos**:
   - Decide si los turnos son:
     - **Rotativos**: Cada jugador actúa en un orden fijo.
     - **Aleatorios**: Los turnos se sortean cada ronda.

## **Funciones opcionales para mayor complejidad**

1. **Modos de juego**:
   - **Por equipos**: Los jugadores humanos pueden formar alianzas.
   - **Mapas dinámicos**: Obstáculos que aparecen y modifican el entorno.
2. **Interfaz gráfica**:
   - Usa una biblioteca como **JavaFX** para agregar animaciones o una interfaz más atractiva.
3. **Sistema de recompensas**:
   - Introduce logros y bonificaciones, como desbloquear personajes o herramientas al ganar.

## **Puntos extra y originalidad**

1. **Generación de mapas**:
   - Un sistema que coloque obstáculos aleatorios o zonas restringidas en cada turno.
2. **Inteligencia artificial básica**:
   - Define estrategias para los jugadores máquina, como atacar al más débil o usar herramientas según el contexto.
3. **Simulación avanzada**:
   - Una opción para ver los combates de forma automática sin intervención del jugador.

## **Plan de implementación**

1. **Primera fase (estructura básica)**:
   - Implementar las clases principales (`Personaje`, `Herramienta`, `Partida`).
   - Diseñar un menú de texto en consola para las selecciones iniciales.
2. **Segunda fase (funcionalidades avanzadas)**:
   - Añadir logs y la funcionalidad de guardar en archivo.
   - Programar los turnos y las condiciones de victoria.
3. **Tercera fase (mejoras visuales y usabilidad)**:
   - Desarrollar una interfaz gráfica, si es parte del alcance.
   - Implementar modos de juego adicionales.
