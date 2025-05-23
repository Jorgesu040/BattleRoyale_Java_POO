package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class Botiquin extends Item {
    
    public static final String NOMBRE = "Botiquín";
    protected Integer valorEfecto = 50;

    public Botiquin() {
        super(Botiquin.NOMBRE);
    }
    
    @Override
    public void usar(Personaje personaje) {
        if (!haSidoUsada) {
            personaje.incrementarVida(valorEfecto);
            haSidoUsada = true;
        }
    }

    @Override
    public void revertir(Personaje personaje) {
        return;
    }
}
