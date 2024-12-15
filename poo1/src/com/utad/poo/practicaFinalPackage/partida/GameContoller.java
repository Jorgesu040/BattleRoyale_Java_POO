package com.utad.poo.practicaFinalPackage.partida;

import com.utad.poo.practicaFinalPackage.interfazGrafica.*;
import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class GameContoller {

    private Turno turno;
    private Personaje jugador;
    private GameArranger gameArranger;
    private GraphicWindowManager graphicWindowManager;
    private MapGenerator mapa;

    public GameContoller(MapGenerator mapa) {
        this.graphicWindowManager = new GraphicWindowManager(mapa);
        this.gameArranger = new GameArranger();
        this.mapa = mapa;
        this.jugador = null;
    }

    public synchronized void startGame() {
        // Inicializar elementos del juego
        gameArranger.startGame();
        // Obtener personajes
        jugador = gameArranger.getPersonajes().get(0);
        graphicWindowManager.setupGame(jugador);
        
        mapa.generateMap();
        // Poner al personaje en el mapa
        mapa.setPlayers(gameArranger.getPersonajes());
        // Configurar la ventana gráfica
        graphicWindowManager.updateInventoryPanel(jugador); // Actualizar la ventana gráfica
    }

    public void ejecutarTurno() {
        // Iniciar turno
        turno = new Turno(gameArranger.getPersonajes());
        turno.iniciarTurno();
        // Ejecutar acciones
        turno.ejecutarAcciones();
        // Mover personajes
        turno.moverPersonajes();

        graphicWindowManager.updateInventoryPanel(jugador); // Actualizar la ventana gráfica
    }

    public static void main(String[] args) {
        GameContoller gameController = new GameContoller(new MapGenerator(7, 1, 1, 3, 4, new Utility()));
        gameController.startGame();
    }



    
}
