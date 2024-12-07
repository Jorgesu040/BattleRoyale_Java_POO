package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public abstract class Pocion extends Item {
    protected Double valorEfecto;

    public Pocion(String nombre, Double valorEfecto) {
        super(nombre);
        this.valorEfecto = valorEfecto;
    }

    public abstract void usar(Personaje personaje);

    // Getters y setters
    public Double getValorEfecto() {
        return valorEfecto;
    }

    public void setValorEfecto(Double valorEfecto) {
        this.valorEfecto = valorEfecto;
    }

}
