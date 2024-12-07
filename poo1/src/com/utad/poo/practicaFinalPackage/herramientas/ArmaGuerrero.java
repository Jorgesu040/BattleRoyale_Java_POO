
package com.utad.poo.practicaFinalPackage.herramientas;

public abstract class ArmaGuerrero extends Arma {

    protected Double iraEspartanaContraataque;

    public ArmaGuerrero(String nombre, Double danio, Double precision, Double iraEspartanaContraataque) {
        super(nombre, danio, precision);
        this.iraEspartanaContraataque = iraEspartanaContraataque;
    }

    public Double getIraEspartanaContraataque() {
        return this.iraEspartanaContraataque;
    }

    public void setIraEspartanaContraataque(Double iraEspartanaContraataque) {
        this.iraEspartanaContraataque = iraEspartanaContraataque;
    }

}