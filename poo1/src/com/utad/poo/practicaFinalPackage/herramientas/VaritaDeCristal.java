
package com.utad.poo.practicaFinalPackage.herramientas;

public class VaritaDeCristal extends ArmaMago {

    public static final String NOMBRE = "Varita de Cristal";
    public static final Double DANYO = 20.0; // pts. -> Hace 20 puntos de daño
    public static final Double PRECISION = 50.0; // % -> 50% de precisión
    public static final Double PROB_CRITICO = 10.0; // +% -> +10 puntos al porcentaje de probabilidad de crítico
    
    public VaritaDeCristal() {
        super(VaritaDeCristal.NOMBRE, VaritaDeCristal.DANYO, VaritaDeCristal.PRECISION, VaritaDeCristal.PROB_CRITICO);
    }   

}