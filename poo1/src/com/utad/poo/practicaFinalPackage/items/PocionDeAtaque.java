package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class PocionDeAtaque extends Pocion {

    public static final String NOMBRE = "Poción de ataque";

    public PocionDeAtaque(Double valorEfecto) {
        super(PocionDeAtaque.NOMBRE, valorEfecto);
    }

    @Override
    public void usar(Personaje personaje) {
        if (!haSidoUsada) {
            personaje.incrementarAtaque(valorEfecto);
            haSidoUsada = true;
        }
    }
}
