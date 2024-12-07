
package com.utad.poo.practicaFinalPackage.herramientas;

public abstract class Herramienta {
    protected String nombre;
    
    public Herramienta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  

}