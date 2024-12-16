package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class TrampaReducirDefensa extends Trampa {

    public static final String NOMBRE = "Trampa de Reducir Defensa";
    public static final Double VALOR_EFECTO = 20.0; // -20.0 % de defensa

    public TrampaReducirDefensa(Double valorEfecto) {
        super(TrampaReducirDefensa.NOMBRE, valorEfecto);
    }

    @Override
    public void usar(Personaje personaje) {
        personaje.decrementarDefensa(super.valorEfecto);
    }

    @Override
    public void revertir(Personaje personaje) {
        personaje.incrementarDefensa(super.valorEfecto);
    }
}
