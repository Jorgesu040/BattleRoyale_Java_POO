
package com.utad.poo.practicaFinalPackage;

public abstract class Arma extends Herramienta {
    
    // Daño que inflige el arma
    protected Double danio;
    // Probabilidad de acertar un golpe
    protected Double precision;

    public Arma(String nombre, Double danio, Double precision) {
        super(nombre);
        this.danio = danio;
        this.precision = precision;
    }
    

}