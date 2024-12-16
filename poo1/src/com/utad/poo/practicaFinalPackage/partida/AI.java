package com.utad.poo.practicaFinalPackage.partida;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;
import com.utad.poo.practicaFinalPackage.personajes.EstadoPersonaje;
import com.utad.poo.practicaFinalPackage.interfazGrafica.Tile;
import com.utad.poo.practicaFinalPackage.interfazGrafica.MapGenerator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class AI {

    private List<Personaje> personajes;
    private MapGenerator map;
    private Random random;

    public AI(List<Personaje> personajes, MapGenerator map) {
        this.personajes = personajes;
        this.map = map;
        this.random = new Random();
    }

    public void decideActions() {
        for (Personaje personaje : personajes) {
            List<Tile> validMoves = new ArrayList<>();
            List<Tile> enemyTiles = new ArrayList<>();

            Tile currentTile = personaje.getUbicacionPersonaje();

            // Obtener tiles adyacentes
            for (Tile tile : map.getTiles()) {
                if (currentTile.isLegalMove(tile)) {
                    validMoves.add(tile);
                }
            }

            // Obtener tiles con enemigos adyacentes
            for (Tile tile : map.getTiles()) {
                if (currentTile.isLegalAction(tile) && tile.getObjectoOcupado() instanceof Personaje) {
                    Personaje targetPersonaje = (Personaje) tile.getObjectoOcupado();
                    // Si el objetivo no es el mismo personaje
                    if (!targetPersonaje.equals(personaje)) {
                        enemyTiles.add(tile);
                    }
                }
            }
                    

            if (!enemyTiles.isEmpty()) {
                Tile targetTile = enemyTiles.get(random.nextInt(enemyTiles.size()));
                Integer action = random.nextInt(3); // 0: atacar, 1: defender, 2: retirarse
                
                // TODO: eliminar esta linea después de probar
                // action = 2; // Testing 
                personaje.setTargetTile(targetTile);
                switch (action) {
                    case 0:
                        personaje.setEstado(EstadoPersonaje.ATACANDO);
                        break;
                    case 1:
                        personaje.setEstado(EstadoPersonaje.DEFENDIENDO);
                        break;
                    case 2:
                        personaje.setEstado(EstadoPersonaje.RETIRANDOSE);
                        break;
                }
            } else if (!validMoves.isEmpty()) {
                Tile moveTile = validMoves.get(random.nextInt(validMoves.size()));
                personaje.setTargetTile(moveTile);
                personaje.setEstado(EstadoPersonaje.MOVIENDOSE); // Estado para movimiento
            }
        }
    }
}
