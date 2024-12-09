package com.utad.poo.practicaFinalPackage.herramientas;

public class Escudo implements Herramienta 
{

	protected String nombre;
	protected Double defensa;
	protected Double probabilidadEscape;
	
	public Escudo(String nombre, Double defensa, Double prob_escape)
	{
		this.nombre = nombre;
		this.defensa = defensa;
		this.probabilidadEscape = prob_escape;
	}
	

	public void setDefensa(Double defensa) 
	{
		this.defensa = defensa;
	}
	
	public Double getDefensa() 
	{
		return this.defensa;
	}


	public Double getProbabilidadEscape() 
	{
		return this.probabilidadEscape;
	}

	
	public void setProbabilidadEscape(Double probabilidadEscape) 
	{
		this.probabilidadEscape = probabilidadEscape;
	}
	
	@Override
	public String getNombre() 
	{
		return this.nombre;
	}
	@Override
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Escudo [nombre=" + this.nombre + ", defensa=" + this.defensa + ", probabilidadEscape=" + this.probabilidadEscape + "]";
	}
	
}
