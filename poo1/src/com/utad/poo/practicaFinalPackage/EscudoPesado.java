
package com.utad.poo.practicaFinalPackage;

public class EscudoPesado extends Escudo {
    public static final String NOMBRE = "Escudo Pesaado";
    public static final Double DEFENSA = 40.0; // pts. -> Defiende 10 puntos de daño
    public static final Double BONUS_DE_RETIRADA = -10.0; // % -> -10 puntos al porcentaje de probabilidad de retirada

    public EscudoPesado() {
        super(EscudoPesado.NOMBRE, EscudoPesado.DEFENSA, EscudoPesado.BONUS_DE_RETIRADA);
    }
}