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

    public Boolean areAllDead() {

        return enemigos.isEmpty();

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
