package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class PocionDeAtaque extends Pocion {

    public static final String NOMBRE = "Poción de ataque";
    public static final Double VALOR_EFECTO = 30.0; // %

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

    @Override
    public void revertir(Personaje personaje) {
        personaje.decrementarAtaque(valorEfecto);
    }
}
