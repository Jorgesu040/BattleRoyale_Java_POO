
package com.utad.poo.practicaFinalPackage;

public abstract class ArmaGuerrero extends Arma {

    protected Double iraEspartanaContraataque;

    public ArmaGuerrero(String nombre, Double danio, Double precision, Double iraEspartanaContraataque) {
        super(nombre, danio, precision);
        this.iraEspartanaContraataque = iraEspartanaContraataque;
    }

}