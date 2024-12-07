package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

// Pocion que incrementa la defensa del personaje por un turno
public class PocionDeDefensa extends Pocion {

    public static final String NOMBRE = "Poción de defensa";

    public PocionDeDefensa(Double valorEfecto) {
        super(PocionDeDefensa.NOMBRE, valorEfecto);
    }

    @Override
    public void usar(Personaje personaje) {
        if (!haSidoUsada) {
            personaje.incrementarDefensa(valorEfecto);
            haSidoUsada = true;
        }
    }
}