
package com.utad.poo.practicaFinalPackage.herramientas;

public final class EscudoLigero extends Escudo {

    public static final String NOMBRE = "Escudo Ligero";
    public static final Double DEFENSA = 50.0d; // % -> Defiende el 70% de daño
    public static final Double BONUS_DE_RETIRADA = 0.2d; // % -> +0 puntos al porcentaje de probabilidad de retirada
    
    public EscudoLigero()
    {
    	super(EscudoLigero.NOMBRE, EscudoLigero.DEFENSA, EscudoLigero.BONUS_DE_RETIRADA);
    }
}