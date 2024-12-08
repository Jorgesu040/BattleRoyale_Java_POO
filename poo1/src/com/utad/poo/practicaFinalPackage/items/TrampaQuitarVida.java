package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class TrampaQuitarVida extends Trampa {

    public static final String NOMBRE = "Trampa de Quitar Vida";
    public static final Double VALOR_EFECTO = 20.0; // -20.0 pts de vida


    public TrampaQuitarVida(Double valorEfecto) {
        super(TrampaQuitarVida.NOMBRE, valorEfecto);
    }

    @Override
    public void usar(Personaje personaje) {
        personaje.decrementarVida(valorEfecto.intValue());
    }

    @Override
    public void revertir(Personaje personaje) {
        return;
    }

    
}
