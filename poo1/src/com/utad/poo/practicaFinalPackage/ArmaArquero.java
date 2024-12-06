
package com.utad.poo.practicaFinalPackage;

public abstract class ArmaArquero extends Arma {

    protected Double punteria;

    public ArmaArquero(String nombre, Double danio, Double precision, Double punteria) {
        super(nombre, danio, precision);
        this.punteria = punteria;
    }


}