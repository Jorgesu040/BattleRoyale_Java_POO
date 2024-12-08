package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class Vendas extends Item {
    
    public static final String NOMBRE = "Vendas";
    protected Integer valorEfecto = 20;

    public Vendas() {
        super(Vendas.NOMBRE);
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
