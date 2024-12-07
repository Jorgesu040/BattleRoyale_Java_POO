package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public abstract class Trampa extends Item {

    protected Double valorEfecto;

    public Trampa(String nombre, Double valorEfecto) {
        super(nombre);
        this.valorEfecto = valorEfecto;
    }

    @Override
    public abstract void usar(Personaje personaje);

    public Double getValorEfecto() {
        return this.valorEfecto;
    }
}
