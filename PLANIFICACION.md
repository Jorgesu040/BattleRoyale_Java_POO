# Planificacion / ideas del proyecto

## **Fase de diseño básico**

1. **Diseño de clases**: HECHO!
   - Crea una jerarquía con una clase base `Personaje` y subclases como:
     - `Guerrero`: Mayor probabilidad de contraataque.
     - `Arquero`: Menor probabilidad tiene el enemigo de retirarse.
     - `Mago`: Empieza con dos pociones y puede hacer ataques criticos.
   - Diseña la interfaz base `Herramienta` y las clases que la implementan:
     - `Arma`: otra Interfaz que extiende de `Herramienta` y que es implementada por:
         - `Armas de Guerrero`: Las armas exclusivas de los guerreros.
         - `Armas de Arquero`: Las armas exclusivas de los arqueros.
         - `Armas de Mago`: Las armas exclusivas de los magos.
     - `Escudos`: clase base que se comporta (`implements`) y que es extendida por tres tipos de escudo globales que afectan a las retaguardias de los personajes:
         - `Escudo Ligero`: Poca defensa, mayor probabilidad de retirada.
         - `Escudo Medio`: Defensa media, probabilidad media de retirada.
         - `Escudo Pesado`: Mayor defensa, menor probabilidad (negativa) de retirada.
     - `Item`: Pociones que se pueden encontrar en el juego en los designados tiles de mapa.
         - En las casillas de mapa se pueden encontrar:
            - Pociones:
               1. De curacion
               2. De defensa
               3. De mayor ataque
            - Trampas: que al finalizar un turno se aplican sobre un `Personaje` (vivo) al azar; el comportamiento es el siguiente:
               - El `Personaje` elegido, tiene un 50% de caer en la trampa que aplica un efecto permante sobre dicho `Personaje`
                  1. Quitar vida
                  2. Reducir defensa
                  3. Reducir ataque

                  ``` java
                  // TODO: Las tramapas deberian ser invisibles ?
                  ```

2. **Niveles de dificultad**: HACER
   - Implementa un sistema que aumente los atributos de los personajes controlados por máquina en dificultades más altas, como más vida o daño.
   - Como se nos puede ir de las manos el tema de dificultad, yo lo haria con unos valores base (dificultad normal), aplicamos porcentajes sobre datos (por ej. el daño que recibe el jugador puede ser menor o encontrar mejores objetos)
      - Una clase va sistematicamente por cada enemigo y le aplica un porcentaje de mejora o reduccion de sus atributos.
         - Baja: 20% menos de daño recibido
         - Normal: 0% de modificacion
         - Alta: 20% mas de daño recibido

3. **Sistema de turnos**: HACER
   - Crea un sistema que permita al jugador humano seleccionar los tiles contiguos a su personaje para moverse y atacar.

   ``` java
   // TODO: Añadir el movimiento de los personajes
   // ¿Deberia ser parte de la clase Personaje, o gestionarlo el MapGenerator/MapController?
   ```

## **Funciones opcionales para mayor complejidad**

1. **Modos de juego**:
   - **Multijugador**: Permite a dos jugadores competir en la misma partida. OPCIONAL
   - **Mapas dinámicos**: Obstáculos que aparecen y modifican el entorno. HECHO! (Sergio GOTY)
2. **Interfaz gráfica**: HECHO!
   - Usa una biblioteca como **JavaFX** para agregar animaciones o una interfaz más atractiva.
3. **Sistema de recompensas**: ESTO LO DIJO CHAT-GTP PERO HABRIA QUE CONSIDERARLO
   - Introduce logros y bonificaciones, como desbloquear personajes o herramientas al ganar.

## **Puntos extra y originalidad**

1. **Generación de mapas**:
   - Un sistema que coloque obstáculos aleatorios o zonas restringidas en cada turno. HECHO!
2. **Inteligencia artificial básica**: HACER
   - Define estrategias para los jugadores máquina, como atacar al más débil o usar herramientas según el contexto.
3. **Simulación avanzada**: OPCIONAL
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
