
package com.utad.poo.practicaFinalPackage.herramientas;

public class ArcoDePrecision extends ArmaArquero {
    
    public static final String NOMBRE = "Arco de precisión";
    public static final Double DANYO = 10.0; // pts. -> Hace 15 puntos de daño
    public static final Double PRECISION = 100.0; // % -> 90% de precisión
    public static final Double PUNTERIA = 10.0; // +% -> +10 puntos al porcentaje de puntería

    public ArcoDePrecision() {
        super(ArcoDePrecision.NOMBRE, ArcoDePrecision.DANYO, ArcoDePrecision.PRECISION, ArcoDePrecision.PUNTERIA);
    }
}