package com.utad.poo.practicaFinalPackage.partida;

import java.util.ArrayList;
import java.util.List;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class GameArranger {

    private List<Personaje> personajes = new ArrayList<Personaje>();
    private List<Personaje> enemigos = new ArrayList<Personaje>();

    public GameArranger() {
    }

    public void startGame() {
        personajes.add((new UserSetup()).askUserSettings());
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

    
    public List<Personaje> getPersonajes() {
        return this.personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public List<Personaje> getEnemigos() {
        return this.enemigos;
    }

    public void setEnemigos(List<Personaje> enemigos) {
        this.enemigos = enemigos;
    }

    
}
