
package com.utad.poo.practicaFinalPackage.herramientas;

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

    public Double getDanio() {
        return danio;
    }

    public void setDanio(Double danio) {
        this.danio = danio;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }
    

}