package com.utad.poo.practicaFinalPackage;

public class ArcoDeGuerrila extends ArmaArquero {
    
    public static final String NOMBRE = "Arco de guerrilla";
    public static final Double DANYO = 15.0; // pts. -> Hace 15 puntos de daño
    public static final Double PRECISION = 90.0; // % -> 90% de precisión
    public static final Double PUNTERIA = 2.0; // +% -> +10 puntos al porcentaje de puntería
    
    public ArcoDeGuerrila() {
        super(ArcoDeGuerrila.NOMBRE, ArcoDeGuerrila.DANYO, ArcoDeGuerrila.PRECISION, ArcoDeGuerrila.PUNTERIA);
    }
}
