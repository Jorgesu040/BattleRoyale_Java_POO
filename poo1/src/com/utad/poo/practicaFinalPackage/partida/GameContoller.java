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

    public void startGame() {
        // Inicializar elementos del juego
        gameArranger.startGame();
        
        mapa.setPlayers(gameArranger.getPersonajes());
        jugador = gameArranger.getPersonajes().get(0);
        
        // Poner al personaje en el mapa

        // Configurar la ventana gráfica
        graphicWindowManager.setupGame(jugador);
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
