
package com.utad.poo.practicaFinalPackage;

public class OrbeAncestral extends ArmaMago {
    public static final String NOMBRE = "Orbe Ancestral";
    public static final Double DANYO = 15.0; // pts. -> Hace 15 puntos de daño
    public static final Double PRECISION = 90.0; // % -> 90% de precisión
    public static final Double PROB_CRITICO = 5.0; // +% -> +5 puntos al porcentaje de probabilidad de crítico

    public OrbeAncestral() {
        super(OrbeAncestral.NOMBRE, OrbeAncestral.DANYO, OrbeAncestral.PRECISION , OrbeAncestral.PROB_CRITICO);
    }

}