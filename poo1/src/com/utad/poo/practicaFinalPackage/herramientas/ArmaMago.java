package com.utad.poo.practicaFinalPackage.herramientas;

public abstract class ArmaMago implements Arma {

    protected Double probabilidadCritico;
    
    protected String nombre;
    protected Double danio;
    protected Double precision;

    public ArmaMago(String nombre, Double danio, Double precision, Double probabilidadCritico) {
        this.probabilidadCritico = probabilidadCritico;
        this.nombre = nombre;
        this.danio = danio;
        this.precision = precision;
    }

    public Double getProbabilidadCritico() {
        return this.probabilidadCritico;
    }

    public void setProbabilidadCritico(Double probabilidadCritico) {
        this.probabilidadCritico = probabilidadCritico;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Override
	public Double getSpecialEffect() {
		return this.probabilidadCritico;
	}

	@Override
	public String toString() {
		return "ArmaMago [probabilidadCritico=" + probabilidadCritico + ", nombre=" + nombre + ", danio=" + danio
				+ ", precision=" + precision + "]";
	}
        
}
