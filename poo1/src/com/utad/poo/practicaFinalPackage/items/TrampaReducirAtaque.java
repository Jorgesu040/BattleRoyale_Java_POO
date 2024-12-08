package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class TrampaReducirAtaque extends Trampa {
    
    public static final String NOMBRE = "Trampa de Reducir Ataque";
    public static final Double VALOR_EFECTO = 20.0; // -20.0 % de ataque

    public TrampaReducirAtaque(Double valorEfecto) {
        super(TrampaReducirAtaque.NOMBRE, valorEfecto);

    }

    @Override
    public void usar(Personaje personaje) {
        personaje.decrementarAtaque(valorEfecto);
    }

    @Override
    public void revertir(Personaje personaje) {
        personaje.incrementarAtaque(valorEfecto);
    }
}
