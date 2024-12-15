
package com.utad.poo.practicaFinalPackage.herramientas;

public abstract class ArmaArquero implements Arma {

    protected Double punteria;
    protected String nombre;
    protected Double danio;
    protected Double precision;

    public ArmaArquero(String nombre, Double danio, Double precision, Double punteria) {
        this.punteria = punteria;
        this.nombre = nombre;
        this.danio = danio;
        this.precision = precision;
    }

    public Double getPunteria() {
        return this.punteria;
    }

    public void setPunteria(Double punteria) {
        this.punteria = punteria;
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
		return this.punteria;
	}

	@Override
	public String toString() {
		return "ArmaArquero [punteria=" + punteria + ", nombre=" + nombre + ", danio=" + danio + ", precision="
				+ precision + "]";
	}


}