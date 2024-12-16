
package com.utad.poo.practicaFinalPackage.herramientas;

public final class BastonDeSabiduria extends ArmaMago {
    
    public static final String NOMBRE = "Bastón de Sabiduría"; 
    public static final Double DANYO = 40.0; // pts. -> Hace 20 puntos de daño
    public static final Double PRECISION = 100.0; // % -> 100% de precisión
    public static final Double PROB_CRITICO = 0.0; // +% -> +0 puntos al porcentaje de probabilidad de crítico
    
    public BastonDeSabiduria() {
        super(BastonDeSabiduria.NOMBRE, BastonDeSabiduria.DANYO, BastonDeSabiduria.PRECISION, BastonDeSabiduria.PROB_CRITICO);
    }

}