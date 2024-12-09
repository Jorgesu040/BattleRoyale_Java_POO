package com.utad.poo.practicaFinalPackage.herramientas;

public interface Arma extends Herramienta
{
	public static Double DEFAULT_DANIO = 0.0d;
	public static Double DEFAULT_PRECISION = 0.0d;
	
	public Double getDanio();
	public void setDanio(Double danio);
	
	public Double getPrecision();
	public void setPrecision(Double precision);
	
}
