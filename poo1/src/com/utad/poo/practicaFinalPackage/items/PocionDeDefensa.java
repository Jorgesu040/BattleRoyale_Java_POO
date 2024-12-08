package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

// Pocion que incrementa la defensa del personaje por un turno
public class PocionDeDefensa extends Pocion {

    public static final String NOMBRE = "Poción de defensa";
    public static final Double VALOR_EFECTO = 30.0; // %

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

    @Override
    public void revertir(Personaje personaje) {
        personaje.decrementarDefensa(valorEfecto);
    }
}