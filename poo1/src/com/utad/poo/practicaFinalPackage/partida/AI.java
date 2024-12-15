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
            Tile currentTile = personaje.getUbicacionPersonaje();

            // Obtener tiles adyacentes
            for (Tile tile : map.getTiles()) {
                if (currentTile.isLegalMove(tile)) {
                    validMoves.add(tile);
                }
            }

            // Buscar enemigos en los tiles válidos
            List<Tile> enemyTiles = new ArrayList<>();
            for (Tile tile : validMoves) {
                if (tile.getOcupado() && tile.getObjectoOcupado() instanceof Personaje) {
                    Personaje otroPersonaje = (Personaje) tile.getObjectoOcupado();
                    if (!personajes.contains(otroPersonaje)) {
                        enemyTiles.add(tile);
                    }
                }
            }

            if (!enemyTiles.isEmpty()) {
                Tile targetTile = enemyTiles.get(random.nextInt(enemyTiles.size()));
                int action = random.nextInt(3); // 0: atacar, 1: defender, 2: retirarse
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
                personaje.setEstado(EstadoPersonaje.NADA); // Estado para movimiento
            }
        }
    }
}
