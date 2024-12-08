package com.utad.poo.practicaFinalPackage.items;

import com.utad.poo.practicaFinalPackage.personajes.Personaje;

// Al tener dicha clase podemos tener una lista de items en la clase Personaje, y luego podemos ya tratarlos de manera específica
public abstract class Item {

    public static final boolean ESTADO_INICIAL_ITEM = false;
    protected String nombre;
    protected boolean haSidoUsada;
    
    public Item(String nombre) {
        this.nombre = nombre;
        this.haSidoUsada = Item.ESTADO_INICIAL_ITEM;
    }

    public abstract void usar(Personaje personaje);

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isHaSidoUsada() {
        return this.haSidoUsada;
    }

    public void setHaSidoUsada(boolean haSidoUsada) {
        this.haSidoUsada = haSidoUsada;
    }

    public abstract void revertir( Personaje personaje );

}
