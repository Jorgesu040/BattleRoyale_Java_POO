
package com.utad.poo.practicaFinalPackage.herramientas;

public final class LanzaPuntiaguda extends ArmaGuerrero {
    
    public static final String NOMBRE = "Lanza Puntiaguda";
    public static final Double DANYO = 30.0; // pts. -> Hace 15 puntos de daño
    public static final Double PRECISION = 90.0; // % -> 90% de precisión
    public static final Double IRA_ESPARTANA_CONTRAATAQUE = 0.0; // +% -> +0 puntos al porcentaje de ira espartana de contraataque

    public LanzaPuntiaguda() {
        super(LanzaPuntiaguda.NOMBRE, LanzaPuntiaguda.DANYO, LanzaPuntiaguda.PRECISION, LanzaPuntiaguda.IRA_ESPARTANA_CONTRAATAQUE);
    }

    

}