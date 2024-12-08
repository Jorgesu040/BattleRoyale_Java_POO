
package com.utad.poo.practicaFinalPackage.herramientas;

public class EscudoLigero extends Escudo {

    public static final String NOMBRE = "Escudo Ligero";
    public static final Double DEFENSA = 50.0; // % -> Defiende el 50% de daño
    public static final Double BONUS_DE_RETIRADA = 10.0; // % -> +10 puntos al porcentaje de probabilidad de retirada

    public EscudoLigero() {
        super(EscudoLigero.NOMBRE, EscudoLigero.DEFENSA, EscudoLigero.BONUS_DE_RETIRADA);
    }
    
}