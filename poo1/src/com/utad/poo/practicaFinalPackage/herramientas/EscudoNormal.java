
package com.utad.poo.practicaFinalPackage.herramientas;

public class EscudoNormal extends Escudo {

    public static final String NOMBRE = "Escudo Normal";
    public static final Double DEFENSA = 20.0; // pts. -> Defiende 10 puntos de daño
    public static final Double BONUS_DE_RETIRADA = 0.0; // % -> +0 puntos al porcentaje de probabilidad de retirada

    public EscudoNormal() {
        super(EscudoNormal.NOMBRE, EscudoNormal.DEFENSA, EscudoNormal.BONUS_DE_RETIRADA);
    }
}