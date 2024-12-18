package com.utad.poo.practicaFinalPackage.herramientas;

public final class Ballesta extends ArmaArquero {

    public static final String NOMBRE = "Ballesta";
    public static final Double DANYO = 30.0; // pts. -> Hace 15 puntos de daño
    public static final Double PRECISION = 70.0; // % -> 90% de precisión
    public static final Double PUNTERIA = 5.0; // +% -> +10 puntos al porcentaje de puntería

    public Ballesta() {
        super(Ballesta.NOMBRE, Ballesta.DANYO, Ballesta.PRECISION, Ballesta.PUNTERIA);
    }

}
