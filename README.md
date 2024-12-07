# Proyecto: Juego de Battle Royale por Turnos

Este proyecto es un **juego de rol por turnos** implementado en Java, donde los jugadores pueden elegir entre diferentes clases de personajes, equipar herramientas y enfrentarse en combates estratégicos.

## Estructura del Proyecto

El proyecto está organizado en paquetes y clases que representan los elementos principales del juego:

### Clases Principales

- Personaje: Clase abstracta que representa a todos los personajes del juego. Define atributos comunes como vida, ataque, defensa y métodos abstractos que deben ser implementados por las clases que heredan de ella.

- Clases que extienden de Personaje:
  - Guerrero: Especializado en combate cuerpo a cuerpo, con alto ataque y defensa. Posee la habilidad especial **"Ira Espartana"**.
  - Arquero: Experto en ataques a distancia, cuenta con el atributo **puntería** que influye en sus acciones.
  - Mago: Utiliza magia para atacar y defenderse. Tiene probabilidades de realizar **ataques críticos**.

### Herramientas y Equipamiento

- Herramienta: Clase base para herramientas que pueden ser equipadas por los personajes.

- **Armas** (Arma):
  - Arma: Clase abstracta para armas, contiene atributos como daño y precisión.
  - Armas específicas por clase de personaje:
    - **Guerrero**:
      - EspadaBastarda
      - LanzaPuntiaguda
      - HachaDobleFilo
    - **Arquero**:
      - ArcoDeGuerrila
      - ArcoDePrecision
      - Ballesta
    - **Mago**:
      - VaritaDeCristal
      - BastonDeSabiduria
      - OrbeAncestral

- **Escudos** (Escudo)
  - Escudo: Clase base para escudos, aumentando la defensa y afectando las probabilidades de retirada.
  - Tipos de escudos
    - EscudoLigero
    - EscudoNormal
    - EscudoPesado

## Mecánicas del Juego

### Sistema de Combate

El juego utiliza un sistema de combate por turnos donde cada personaje puede realizar las siguientes acciones:

- **Atacar**: Inflige daño al oponente considerando el ataque del personaje y el daño del arma equipada.
- **Defenderse**: Reduce el daño recibido mediante la defensa del personaje y el escudo equipado.
- **Contraatacar**: Algunos personajes tienen la posibilidad de devolver parte del daño recibido al oponente.
- **Retirada**: Intento de escapar del combate, con probabilidades influenciadas por equipamiento y características del personaje.

### Habilidades Especiales

- **Guerrero**:
  - **Ira Espartana**: Aumenta la probabilidad de contraataque en un 5% cada vez que recibe daño, hasta un máximo del 30%.
- **Arquero**:
  - **Puntería**: Mejora la precisión de sus ataques y disminuye las probabilidades de contraataque y retirada del oponente.
- **Mago**:
  - **Ataques Críticos**: Tiene una probabilidad inicial del 1% de realizar un ataque crítico que aumenta el daño en un 50%. Esta probabilidad incrementa en un 2% hasta un máximo del 10%.

### Items y Trampas

- **Items**: Los personajes pueden encontrar items que otorgan beneficios como aumento de ataque, defensa o recuperación de vida.
- **Trampas**: Al finalizar un turno, existe la posibilidad de que un personaje caiga en una trampa que aplica efectos negativos permanentes.

## Ideas y Planificación

El proyecto se ha planificado para incluir diversas características que enriquecen la experiencia de juego:

- **Niveles de Dificultad**: Ajustan los atributos de los enemigos para ofrecer un mayor desafío.
- **Sistema de Turnos**: Puede ser rotativo o aleatorio, determinando el orden de las acciones de los personajes.
- **Modos de Juego Adicionales**:
  - **Por Equipos**: Los jugadores pueden formar alianzas.
  - **Mapas Dinámicos**: Implementación de obstáculos y zonas restringidas que cambian cada turno.
- **Inteligencia Artificial Básica**: Los personajes controlados por la máquina siguen estrategias simples, como atacar al oponente más débil.
- **Simulación Avanzada**: Opción para visualizar los combates de forma automática sin intervención del jugador.

Para más detalles sobre la planificación y las ideas del proyecto, consulta el documento PLANIFICACION.md.

## Implementación

El proyecto se desarrollará en varias fases:

1. **Estructura Básica**:
   - Implementación de las clases principales y sus interacciones.
   - Creación de un menú de texto para la interacción básica con el juego.
2. **Funcionalidades Avanzadas**:
   - Añadir logs y la posibilidad de guardar y cargar partidas.
   - Implementar los modos de juego y mecánicas adicionales.
3. **Mejoras Visuales y Usabilidad**:
   - Desarrollo de una interfaz gráfica utilizando bibliotecas como JavaFX.
   - Optimización del código y mejora de la experiencia del usuario.

## Cómo Jugar

1. **Configuración Inicial**:
   - Elige la clase de personaje con la que deseas jugar: Guerrero, Arquero o Mago.
   - Equipa a tu personaje con armas y escudos disponibles.

2. **Desarrollo del Juego**:
   - Participa en combates por turnos contra enemigos controlados por la máquina.
   - Toma decisiones estratégicas en cada turno: atacar, defenderte, contraatacar o retirarte.
   - Administra tus items y mejora tu equipamiento al derrotar enemigos.

3. **Objetivo**:
   - Derrotar a todos los enemigos y superar los desafíos presentados en cada nivel de dificultad.

## Requisitos del Sistema

- **Java SE Development Kit** 8 o superior.
- Un entorno de desarrollo integrado (IDE) como **Eclipse** o **IntelliJ IDEA**.

## Instalación y Ejecución

1. Clona el repositorio del proyecto en tu máquina local.
2. Importa el proyecto en tu IDE preferido.
3. Compila y ejecuta la clase principal del juego.

## Contribución

Si deseas contribuir al proyecto:

- Realiza un fork del repositorio.
- Crea una rama con la nueva funcionalidad o mejora.
- Envía un pull request para revisión.
