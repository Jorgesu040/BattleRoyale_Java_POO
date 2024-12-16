package com.utad.poo.practicaFinalPackage.partida;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.utad.poo.practicaFinalPackage.interfazGrafica.*;
import com.utad.poo.practicaFinalPackage.personajes.Personaje;
import com.utad.poo.practicaFinalPackage.inout.*;

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
        this.turno = null;
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

        if (!this.jugador.estaVivo()) {
            CreateLogs.addLog("Has perdido");
            JOptionPane.showMessageDialog(null, "¡Oh no!, " + this.jugador.getNombre() + ", has sido derrotado y has perdido.", "Derrota", JOptionPane.INFORMATION_MESSAGE);
            CreateLogs.printLogs();
            System.exit(0);
        } else if (gameArranger.areAllDead()) {
            CreateLogs.addLog("Has ganado");
            JOptionPane.showMessageDialog(null, "Felicidades, valiente aventurero, has ganado!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
            CreateLogs.printLogs();
            System.exit(0);
        }
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
        GameContoller gameController = new GameContoller(new MapGenerator(7, 3, 1, 1, 3, new Utility(), new IniciarPartidaFichero(), false));
        gameController.startGame();

        while (true) {            
        }

    }



    
}
