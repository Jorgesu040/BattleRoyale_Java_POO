
package com.utad.poo.practicaFinalPackage;

public class Escudo extends Herramienta {
    
    protected Double Defensa;
    protected Double probabilidadEscape;

    public Escudo(String nombre, Double Defensa, Double probabilidadEscape) {
        super(nombre);
        this.Defensa = Defensa;
        this.probabilidadEscape = probabilidadEscape;
    }

}