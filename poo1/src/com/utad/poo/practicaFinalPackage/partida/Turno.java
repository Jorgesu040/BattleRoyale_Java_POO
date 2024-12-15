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

import com.utad.poo.practicaFinalPackage.personajes.EstadoPersonaje;
import com.utad.poo.practicaFinalPackage.personajes.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Turno {

    private List<Personaje> personajes;

    private List<Personaje> enemigosVivos;

    public Turno(List<Personaje> personajes) {
        this.personajes = personajes;
        enemigosVivos = new ArrayList<Personaje>();
    }

    public void iniciarTurno() {
        ejecutarFaseAcciones();
        ejecutarFaseAtaques();
        ejecutarFaseMovimientos();
        finalizarTurno();
    }

    private void ejecutarFaseAcciones() {
        for (Personaje personaje : personajes) {
            if (personaje.getTargetTile().getOcupado() && personaje.getTargetTile().getObjectoOcupado() instanceof Personaje) {
                if (personaje.getEstado() == EstadoPersonaje.DEFENDIENDO || personaje.getEstado() == EstadoPersonaje.RETIRANDOSE) {
                    personaje.defensa((Personaje) personaje.getTargetTile().getObjectoOcupado());
                }
            }
        }
    }

    private void ejecutarFaseAtaques() {
        for (Personaje personaje : personajes) {
            if (personaje.getTargetTile().getOcupado()
                    && personaje.getTargetTile().getObjectoOcupado() instanceof Personaje) {
                if (personaje.getEstado() == EstadoPersonaje.ATACANDO) {
                    personaje.atacar((Personaje) personaje.getTargetTile().getObjectoOcupado());
                }
            }

        }
    }

    private void ejecutarFaseMovimientos() {
        for (Personaje personaje : personajes) {
            // Lógica para mover personajes
            // Restricción: No pueden atacar o defender en esta fase

            if (personaje.getUbicacionPersonaje().isTrap())
            {
                personaje.getVida(); // hay que cambiar la vida por la trampa
                personaje.getUbicacionPersonaje().explodeTrap();
            }

            personaje.getUbicacionPersonaje().removeTileObject();
            personaje.setUbicacionPersonaje(personaje.getTargetTile());
            personaje.getTargetTile().setTileObject(personaje);


        }
    }

    private void finalizarTurno() {
        for (Personaje personaje : personajes) {
            personaje.setEstado(EstadoPersonaje.NADA);
            personaje.setTargetTile(null);
        }

        for (int i = 1; i < personajes.size(); i++) { // Empezar desde 1 para no contar al primero que es el jugador
            Personaje personaje = personajes.get(i);
            if (personaje.estaVivo()) {
            enemigosVivos.add(personaje);
            } else {
            personaje.getUbicacionPersonaje().removeTileObject();
            }
        }
        // Reiniciar modificadores y limpiar inventario de cada personaje
        for (Personaje personaje : personajes) {
            personaje.reiniciarModificadores(); // Reiniciar modificadores e items usados
        }
    }

    public List<Personaje> getEnemigosVivos() {
        return enemigosVivos;
    }

}
