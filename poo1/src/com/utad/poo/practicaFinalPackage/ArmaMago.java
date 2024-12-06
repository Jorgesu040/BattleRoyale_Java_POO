package com.utad.poo.practicaFinalPackage;

public abstract class ArmaMago extends Arma {

    protected Double probabilidadCritico;

    public ArmaMago(String nombre, Double danio, Double precision, Double probabilidadCritico) {
        super(nombre, danio, precision);
        this.probabilidadCritico = probabilidadCritico;
    }    
}
