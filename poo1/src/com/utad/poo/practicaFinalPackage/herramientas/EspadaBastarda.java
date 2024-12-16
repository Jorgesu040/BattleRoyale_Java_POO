
package com.utad.poo.practicaFinalPackage.herramientas;

public final class EspadaBastarda extends ArmaGuerrero {
    
    public static final String NOMBRE = "Espada Bastarda";
    public static final Double DANYO = 30.0; // pts. -> Hace 15 puntos de daño
    public static final Double PRECISION = 90.0; // % -> 90% de precisión
    public static final Double IRA_ESPARTANA_CONTRAATAQUE = 10.0; // +% -> +10 puntos al porcentaje de ira espartana de contraataque

    public EspadaBastarda() {
        super(EspadaBastarda.NOMBRE, EspadaBastarda.DANYO, EspadaBastarda.PRECISION, EspadaBastarda.IRA_ESPARTANA_CONTRAATAQUE);
    }

}
