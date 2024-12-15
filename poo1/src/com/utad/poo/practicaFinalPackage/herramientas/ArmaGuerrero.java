
package com.utad.poo.practicaFinalPackage.herramientas;

public abstract class ArmaGuerrero implements Arma {

    protected Double iraEspartanaContraataque;
    protected String nombre;
    protected Double danio;
    protected Double precision;

    public ArmaGuerrero(String nombre, Double danio, Double precision, Double iraEspartanaContraataque) {
        this.iraEspartanaContraataque = iraEspartanaContraataque;
        this.nombre = nombre;
        this.danio = danio;
        this.precision = precision;
    }

    public Double getIraEspartanaContraataque() {
        return this.iraEspartanaContraataque;
    }

    public void setIraEspartanaContraataque(Double iraEspartanaContraataque) {
        this.iraEspartanaContraataque = iraEspartanaContraataque;
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
        return this.iraEspartanaContraataque;
    }

	@Override
	public String toString() {
		return "ArmaGuerrero [iraEspartanaContraataque=" + iraEspartanaContraataque + ", nombre=" + nombre + ", danio="
				+ danio + ", precision=" + precision + "]";
	}

}