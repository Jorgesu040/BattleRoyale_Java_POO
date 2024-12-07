# TODO List

## 1. Implement methods in `Personaje`

- Complete the `lootearEnemigo` method to allow characters to loot items from defeated enemies.
- Implement the attribute increment methods:
  - `incrementarAtaque(Double valorEfecto)`
  - `incrementarDefensa(Double valorEfecto)`
  - `incrementarProbabilidadRetirada(Double valorEfecto)`

## 2. Complete the character classes

- **Guerrero**:
  - Implement the `defensa`, `contraataque`, and `retirada` methods.
  - Ensure `iraEspartanaContraataque` increases correctly upon receiving damage.
- **Arquero**:
  - Define the `atacar`, `defensa`, `contraataque`, and `retirada` methods.
  - Utilize the `punteria` attribute from `ArmaArquero` to affect attack precision.
- **Mago**:
  - Complete the `defensa`, `contraataque`, and `retirada` methods.
  - Implement the logic for critical attacks based on `probabilidadCritico`.

## 3. Develop the combat mechanics

- Implement the turn-based system to manage player actions.
- Define the logic for calculating damage, including weapon attributes and character modifiers.
- Ensure that methods like `atacar` and `recibirAtaque` correctly interact.

## 4. Create subclasses of `Item`

- **Potions**:
  - `PocionDeCuracion`: Restores health when used.
  - `PocionDeAtaque`: Increases attack strength temporarily.
  - Implement the `usar` method for each potion type.
- **Traps**:
  - Create trap items that negatively affect characters.
  - Define how traps are triggered and their effects.

## 5. Implement equipment change mechanics

- Allow characters to change their `Escudo`.
- Ensure that new equipment correctly impacts character stats.

## 6. Build the game flow

- Create a `Partida` class to control the game progression.
- Manage the initialization of characters, distribution of turns, and end-game conditions.

## 7. Handle traps and environmental effects

- Implement the logic for traps affecting characters at the end of turns, as mentioned in your `PLANIFICACION.md`.
- Define how environmental effects alter gameplay.

## 8. Enhance error handling and input validation

- Validate inputs from users to prevent invalid data.
- Add exception handling for unforeseen errors during gameplay.

## 9. Develop a user interface

- Start with a console-based menu for player interaction.
- Consider planning for a graphical interface in future updates.

## 10. Update documentation

- Revise the `README.md` to reflect current progress and any changes.
- Document new classes and methods added to the project.

## 11. Optimize and refactor code

- Review code for consistency in style and naming conventions.
- Refactor redundant or inefficient code segments.
- Ensure comments are up-to-date and helpful.

## 12. Write unit tests

- Create test cases for critical methods to ensure they work as intended.
- Implement tests for combat mechanics, item usage, and character actions.
