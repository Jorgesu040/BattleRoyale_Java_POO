package com.utad.poo.practicaFinalPackage.herramientas;


import java.util.ArrayList;
import java.util.List;


public class ClasesTesting 
{

	public static void main(String[] args) 
	{
	
		Arma arco1 = new ArcoDeGuerrilla();
		Arma arco2 = new ArcoDePrecision();
		Arma arco3 = new Ballesta();
		
		Arma baston = new BastonDeSabiduria();
		Arma hacha = new HachaDobleFilo();
		Arma lanza = new LanzaPuntiaguda();
		Arma orbe = new OrbeAncestral();
		Arma varita = new VaritaDeCristal();
		
		Herramienta escudo = new Escudo("Escudo Tactico", 160.0d, 0.7d);
		
		List<Arma> armas = new ArrayList<Arma>();
		
		armas.add(arco1);
		armas.add(arco2);
		armas.add(arco3);
		armas.add(baston);
		armas.add(hacha);
		armas.add(lanza);
		armas.add(orbe);
		armas.add(varita);
		
		System.out.println(escudo);
		
		for (Arma myArma : armas)
		{
			System.out.println(myArma);
		}
		
	}
	
	
}
