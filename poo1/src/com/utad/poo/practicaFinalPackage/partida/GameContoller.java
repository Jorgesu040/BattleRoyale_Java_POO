package com.utad.poo.practicaFinalPackage.partida;

import java.util.ArrayList;
import java.util.List;

import com.utad.poo.practicaFinalPackage.interfazGrafica.*;
import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class GameContoller implements EmpezarTurnoEventListener, TileClickListener {

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

        gameArranger.setEnemigos(mapa.getBandidos());

        graphicWindowManager.setGameEventListener(this);
        graphicWindowManager.getMapController().setTileClickListener(this);
    }

    public void ejecutarTurno() {
        // Iniciar turno
        List<Personaje> allCharacters = new ArrayList<>(gameArranger.getPersonajes());
        allCharacters.addAll(gameArranger.getEnemigos());
        new AI(gameArranger.getEnemigos(), mapa).decideActions();
        turno = new Turno(allCharacters);
        // Descmarcar el tile seleccionado // TODO
        jugador.getTargetTile().setTargetTile(false);
        turno.iniciarTurno();

        // Actualizar los enemigos vivos
        gameArranger.setEnemigos(turno.getEnemigosVivos());
        // Actualizar la ventana gráfica
        graphicWindowManager.updateInventoryPanel(jugador); // Actualizar la ventana inventario
        graphicWindowManager.updateStatsPanel(this.jugador); // Actualizar la ventana de estadísticas
        graphicWindowManager.updateActionsPanel(); // Actualizar la ventana de acciones
        graphicWindowManager.updateMapPanel(); // Actualizar el mapa
        graphicWindowManager.resetActionsPanel(); // Reactivar los botones de acciones


    }

    @Override
    public void onExecuteTurn() {
        ejecutarTurno();
    }

    @Override
    public void onTileClicked(Tile tile) {
        // Asignar el tile seleccionado al personaje
        if ( jugador.getTargetTile() !=null ) {
            jugador.getTargetTile().setTargetTile(false);
        } 
        jugador.setTargetTile(tile);
        // Marcar el tile seleccionado
        jugador.getTargetTile().setTargetTile(true);
    }

    public static void main(String[] args) {
        GameContoller gameController = new GameContoller(new MapGenerator(7, 5, 1, 3, 0, new Utility()));
        gameController.startGame();

        while (gameController.jugador.estaVivo() && !gameController.gameArranger.getEnemigos().isEmpty()) {
        }

        System.out.println("Juego terminado");
    }



    
}
