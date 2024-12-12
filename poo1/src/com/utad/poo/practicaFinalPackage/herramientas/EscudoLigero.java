
package com.utad.poo.practicaFinalPackage.herramientas;

public final class EscudoLigero extends Escudo {

    public static final String NOMBRE = "Escudo Ligero";
    public static final Double DEFENSA = 50.0d; // % -> Defiende el 70% de daño
    public static final Double BONUS_DE_RETIRADA = 20.0d; // % -> +20 puntos al porcentaje de probabilidad de retirada
    
    public EscudoLigero()
    {
    	super(EscudoLigero.NOMBRE, EscudoLigero.DEFENSA, EscudoLigero.BONUS_DE_RETIRADA);
    }
}