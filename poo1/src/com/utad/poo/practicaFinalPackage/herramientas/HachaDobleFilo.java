package com.utad.poo.practicaFinalPackage.herramientas;

public final class HachaDobleFilo extends ArmaGuerrero {

    public static final String NOMBRE = "Hacha de doble filo";
    public static final Double DANYO = 10.0; // pts. -> Hace 15 puntos de daño
    public static final Double PRECISION = 100.0; // % -> 90% de precisión
    public static final Double IRA_ESPARTANA_CONTRAATAQUE = 5.0; // +% -> +5 puntos al porcentaje de ira espartana de contraataque
    
    public HachaDobleFilo() {
        super(HachaDobleFilo.NOMBRE, HachaDobleFilo.DANYO, HachaDobleFilo.PRECISION, HachaDobleFilo.IRA_ESPARTANA_CONTRAATAQUE);
    }
    
}
