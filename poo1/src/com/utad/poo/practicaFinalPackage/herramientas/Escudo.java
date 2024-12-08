
package com.utad.poo.practicaFinalPackage.herramientas;

public class Escudo extends Herramienta {
    
    protected Double defensa;
    protected Double probabilidadEscape;

    public Escudo(String nombre, Double defensa, Double probabilidadEscape) {
        super(nombre);
        this.defensa = defensa;
        this.probabilidadEscape = probabilidadEscape;
    }

    public Double getDefensa() {
        return this.defensa;
    }

    public void setDefensa(Double defensa) {
        this.defensa = defensa;
    }

    public Double getProbabilidadEscape() {
        return this.probabilidadEscape;
    }

    public void setProbabilidadEscape(Double probabilidadEscape) {
        this.probabilidadEscape = probabilidadEscape;
    }


}