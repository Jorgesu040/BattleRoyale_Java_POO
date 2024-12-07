package com.utad.poo.practicaFinalPackage.herramientas;

public abstract class ArmaMago extends Arma {

    protected Double probabilidadCritico;

    public ArmaMago(String nombre, Double danio, Double precision, Double probabilidadCritico) {
        super(nombre, danio, precision);
        this.probabilidadCritico = probabilidadCritico;
    }

    public Double getProbabilidadCritico() {
        return this.probabilidadCritico;
    }

    public void setProbabilidadCritico(Double probabilidadCritico) {
        this.probabilidadCritico = probabilidadCritico;
    }
        
}
