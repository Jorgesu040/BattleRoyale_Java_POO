// PocionRetirada.java
package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class PocionDeRetirada extends Pocion {

    public static final String NOMBRE = "Poción de retirada";

    public PocionDeRetirada(Double valorEfecto) {
        super(PocionDeRetirada.NOMBRE, valorEfecto);
    }

    @Override
    public void usar(Personaje personaje) {
        if (!haSidoUsada) {
            personaje.incrementarProbabilidadRetirada(valorEfecto);
            haSidoUsada = true;
        }
    }
}