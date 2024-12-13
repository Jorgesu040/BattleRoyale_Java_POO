package com.utad.poo.practicaFinalPackage.partida;

import java.util.ArrayList;
import java.util.List;

import com.utad.poo.practicaFinalPackage.interfazGrafica.GraphicWindowManager;
import com.utad.poo.practicaFinalPackage.interfazGrafica.MapGenerator;
import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class GameArranger {

    private GraphicWindowManager graphicWindowManager;
    private List<Personaje> personajes = new ArrayList<Personaje>();
    private List<Personaje> enemigos = new ArrayList<Personaje>();

    public GameArranger(GraphicWindowManager graphicWindowManager) {
        this.graphicWindowManager = graphicWindowManager;
    }

    public void startGame() {
        personajes.add((new UserSetup()).askUserSettings());

        enemigos.add((new BanditSetup()).createRandomBandit()); // Testing
        // Initialize game logic
        // Setup the graphic window
        graphicWindowManager.setupGame();
        System.out.println("Game started " + personajes.get(0).toString()); // Testing
        System.out.println("Game started " + enemigos.get(0).toString()); // Testing
    }

    public void endGame() {
        // // Perform end game logic
        // gameLogicHandler.endGame();
        // // Close the graphic window
        // graphicWindowManager.closeGameWindow();
        return;
    }

    public void restartGame() {
        // Restart the game
        endGame();
        startGame();
    }

    
    // Other methods to arrange the game...

    public List<Personaje> getPersonajes() {
		return this.personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}

	public static void main(String[] args) {
    	MapGenerator mapa = new MapGenerator(7, 1, 1, 3, 4);
        GraphicWindowManager graphicWindowManager = new GraphicWindowManager(mapa);
        GameArranger gameArranger = new GameArranger(graphicWindowManager);
        gameArranger.startGame();
        mapa.setPlayers(gameArranger.getPersonajes());
    }
}
