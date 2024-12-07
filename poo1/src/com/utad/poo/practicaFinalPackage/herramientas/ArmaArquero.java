
package com.utad.poo.practicaFinalPackage.herramientas;

public abstract class ArmaArquero extends Arma {

    protected Double punteria;

    public ArmaArquero(String nombre, Double danio, Double precision, Double punteria) {
        super(nombre, danio, precision);
        this.punteria = punteria;
    }

    public Double getPunteria() {
        return this.punteria;
    }

    public void setPunteria(Double punteria) {
        this.punteria = punteria;
    }


}