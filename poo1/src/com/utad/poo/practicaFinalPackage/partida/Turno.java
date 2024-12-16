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

import com.utad.poo.practicaFinalPackage.inout.CreateLogs;
import com.utad.poo.practicaFinalPackage.items.Item;
import com.utad.poo.practicaFinalPackage.items.Trampa;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



// if (personaje.getTargetTile().getObjectoOcupado() instanceof Personaje) {
//     mensaje = personaje.getNombre() + " se ha defendido de " + ((Personaje) personaje.getTargetTile().getObjectoOcupado()).getNombre();
// } else {
//     mensaje = personaje.getNombre() + " se ha defendido de una casilla en la que no había nadie 【・_・?】";
// }



public class Turno {

    private List<Personaje> personajes;

    private static Integer turnoActual = 0;

    private List<Personaje> enemigosVivos;

    public Turno(List<Personaje> personajes) {
        this.personajes = personajes;
        enemigosVivos = new ArrayList<Personaje>();
    }

    public void iniciarTurno() {

        CreateLogs.addLog("|------------- TURNO " + turnoActual + " -------------|");

        
        ejecutarFaseAcciones();
        ejecutarFaseAtaques();
        ejecutarFaseMovimientos();
        finalizarTurno();
        aplicarEfetosTrampas();

        turnoActual++;

        CreateLogs.addLog("|-----------------------------------|");
    }

    private void aplicarEfetosTrampas() {
        // Aplicar los efectos de las trampas para la siguiente ronda
        for (Personaje personaje : personajes) {
            for (Item item : personaje.getEfectos()) {
                item.usar(personaje);
                if (personaje.getIsAI().equals(false)){
                    JOptionPane.showMessageDialog(null, "Has pisado una " + item.getNombre() + "\nen el siguiente turno tendrás dicho efecto negativo", "Trampa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void ejecutarFaseAcciones() {
        for (Personaje personaje : personajes) {
            String mensaje = null;
                if (personaje.getTargetTile().getOcupado() && personaje.getTargetTile().getObjectoOcupado() instanceof Personaje) {
               
                if (personaje.getEstado() == EstadoPersonaje.DEFENDIENDO) {
                    personaje.defensa((Personaje) personaje.getTargetTile().getObjectoOcupado());
                    mensaje = personaje.getNombre() + " se ha defendido de " + ((Personaje) personaje.getTargetTile().getObjectoOcupado()).getNombre();
                } else if (personaje.getEstado() == EstadoPersonaje.RETIRANDOSE) {
                    personaje.retirada((Personaje) personaje.getTargetTile().getObjectoOcupado());
                    mensaje = personaje.getNombre() + " va a intentar evitar el ataque de " + ((Personaje) personaje.getTargetTile().getObjectoOcupado()).getNombre();
                }
            
            } else if (personaje.getEstado() == EstadoPersonaje.DEFENDIENDO) {
                mensaje = personaje.getNombre() + " se ha defendido de una casilla en la que no había nadie 【・_・?】";
            } else if (personaje.getEstado() == EstadoPersonaje.RETIRANDOSE) {
                mensaje = personaje.getNombre() + " se ha evitado el ataque (retirada) de una casilla en la que no había nadie 【・_・?】";
            }

            if ( mensaje != null) {
                CreateLogs.addLog(mensaje);
            }
        }
    }

    private void ejecutarFaseAtaques() {
        for (Personaje personaje : personajes) {
            String mensaje = null;
            if (personaje.getTargetTile().getOcupado() && personaje.getTargetTile().getObjectoOcupado() instanceof Personaje) {
                if (personaje.getEstado() == EstadoPersonaje.ATACANDO) {
                    personaje.atacar((Personaje) personaje.getTargetTile().getObjectoOcupado());
                    mensaje = personaje.getNombre() + " ha atacado a " + ((Personaje) personaje.getTargetTile().getObjectoOcupado()).getNombre();

                }
            } else if (personaje.getEstado() == EstadoPersonaje.ATACANDO) {
                mensaje = personaje.getNombre() + " ha atacado a una casilla en la que no había nadie 【・_・?】";
            }

            if ( mensaje != null) {
                CreateLogs.addLog(mensaje);
            }

        }
    }

    private void ejecutarFaseMovimientos() {
        for (Personaje personaje : personajes) {
            // Lógica para mover personajes
            // Restricción: No pueden atacar o defender en esta fase

            String mensaje = null;
            String mensajeItem = null;

            if (personaje.getEstado() == EstadoPersonaje.MOVIENDOSE) {

                // TODO: si hay una trampa se ha de añadir a una lista (efectos) del personaje,
                // aplicar el metodo usar del item
                // if (personaje.getUbicacionPersonaje().isTrap()) {
                // personaje.getVida(); // hay que cambiar la vida por la trampa
                // personaje.getUbicacionPersonaje().explodeTrap();
                // }

                if (personaje.getTargetTile().getObjectoOcupado() instanceof Item) {
                    if (personaje.getTargetTile().getObjectoOcupado() instanceof Trampa) {
                        // Añadimos a la lista de trampas afectando al personaje
                        personaje.getEfectos().add((Item) personaje.getTargetTile().getObjectoOcupado());
                        // LLamamos al metodo de explotar la trampa (eliminarla y cambio visual)
                        personaje.getTargetTile().explodeTrap();
                        mensajeItem = personaje.getNombre() + " ha pisado una trampa en la casilla "
                                    + personaje.getTargetTile().getTileId();
                    } else {
                        // Comprobramos que no tiene mas de 5 items
                        if (personaje.getItems().size() < 5) {
                            personaje.getItems().add((Item) personaje.getTargetTile().getObjectoOcupado());
                            // Eliminamos el item de la casilla
                            personaje.getTargetTile().removeTileObject();
                            mensajeItem = personaje.getNombre() + " ha recogido el item de la casilla "
                                    + personaje.getTargetTile().getTileId();
                        } else {
                            mensajeItem = personaje.getNombre() + " no ha podido recoger el item de la casilla "
                                    + personaje.getTargetTile().getTileId() + " porque su inventario está lleno";
                            JOptionPane.showMessageDialog(null, "No puedes recoger más de 5 items", "Inventario lleno",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                    // Comprobar que nadie se ha movido a la casilla ya
                    if (!(personaje.getTargetTile().getObjectoOcupado() instanceof Personaje)) {
                        personaje.getUbicacionPersonaje().removeTileObject();
                        personaje.setUbicacionPersonaje(personaje.getTargetTile());
                        personaje.getTargetTile().setTileObject(personaje);
                        mensaje = personaje.getNombre() + " se ha movido a la casilla "
                                + personaje.getTargetTile().getTileId();
                    } else {
                        mensaje = personaje.getNombre() + " no se ha podido mover a la casilla "
                                + personaje.getTargetTile().getTileId() + " porque "
                                + ((Personaje) personaje.getTargetTile().getObjectoOcupado()).getNombre()
                                + " ha llegado antes : ' (";
                    }
                }
            

            if (mensaje != null) {
                CreateLogs.addLog(mensaje);
            }

            if (mensajeItem != null) {
                CreateLogs.addLog(mensajeItem);
            }
        }
    }

    private void finalizarTurno() {
        for (Personaje personaje : personajes) {
            personaje.setEstado(EstadoPersonaje.NADA);
            personaje.setTargetTile(null);
        }

        for (Personaje personaje : personajes) {
            if (personaje.estaVivo() && personaje.getIsAI()) {
                enemigosVivos.add(personaje);
            } else if (!personaje.estaVivo()) {
                personaje.getUbicacionPersonaje().setSpecialImage(personaje.seleccionarImagen());
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
