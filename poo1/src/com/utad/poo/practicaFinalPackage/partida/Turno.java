/**
 * Clase encargada finalizar el turno
 * La dinamica de un turno es la siguiente:
 *  - Se inicia el turno y se reician los modificadores de los personajes
 *  - Se limpia el inventario de los personajes (los items marcados como usados-False- se eliminan)
 *  (Tras esto la clase a la que pertenece (composition) se encarga de la actualizar la interfaz grafica)
 * 
 *  Una vez establecida la base del turno, el jugador / IA puede realizar las siguientes acciones:
 *  -Atacar a un Tile contiguo
 *  -Defenderse de un Tile contiguo
 *  -Retirarse de un ataque de un Tile contiguo (evitar el daño)
 * 
 * Una vez realizadas las acciones, se pasa a la siguiente fase del turno:
 *  - Despues de ejecutar las acciones, los personaje que se quiere mover, se mueven
 *  - IMP: Los personajes que se mueven, no pueden realizar acciones de ataque o defensa
 *  - IMP: Moverse se ejecuta tras las acciones de ataque, defensa y retirada para evitar que un personaje haga click en tile y resulta que el enemigo se ha movido
 * 
 */


package com.utad.poo.practicaFinalPackage.partida;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;
import java.util.List;

public class Turno {

    private List<Personaje> personajes;

    public Turno(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public void iniciarTurno() {
        // Reiniciar modificadores y limpiar inventario de cada personaje
        for (Personaje personaje : personajes) {
            personaje.reiniciarModificadores(); // Reiniciar modificadores e items usados
        }
    }

    public void ejecutarAcciones() {
        // Lógica para que el jugador o IA realicen acciones:
        // - Atacar a un Tile contiguo
        // - Defenderse de un Tile contiguo
        // - Retirarse de un ataque de un Tile contiguo
    }

    public void moverPersonajes() {
        // Mover personajes que deseen moverse
        // Importante: Los personajes que se mueven no pueden atacar o defender
    }
}
