package com.utad.poo.practicaFinalPackage.personajes;

import com.utad.poo.practicaFinalPackage.items.*;
import com.utad.poo.practicaFinalPackage.herramientas.*;

/* Clase mago:
    - Implementa la lógica de la mecánica de critico de un personaje de tipo Mago.

    Nota:
    - La clase Mago empieza con 2 dos pociones: una de defensa y otra de ataque.

    Equipamiento:
        - Armas de la catergoria ArmaMago: BastonDeSabiduria, OrbeAncestral, VaritaDeCristal
        - Escudos normales: EscudoLigero, EscudoNormal, EscudoPesado

    Características:
    - Hechizo de defensor magico que aumenta su defensa base (defensa +5.0%)
    - Magia runica que aumentar su daño base (ataque +5.0%)
    - Un bastón mágico que le permite lanzar hechizos mágicos y tiene una pequeña probabilidad (2%) hacer un ataque crítico (+50%)
        - La probabilidad de ataque crítico aumenta en +2 hasta un 10%.
*/


public class Mago extends Personaje {

    public static final Double ATAQUE_CRITICO = 50.0;

    public static final Double PROB_CRITICO_INICIAL = 2.0;
    public static final Double PROB_CRITICO_INCREMENTO = 2.0;
    public static final Double PROB_CRITICO_MAX = 10.0;

    public static final Double MOD_ATQ_INICIAL = 5.0;
    public static final Double MOD_DEF_INICIAL = 5.0;

    public static final Integer NUMERO_ITEMS_DEFAULT = 2;

    private Double probablidadCritico;

    public Mago(Arma arma, Escudo escudo) {
        this("Mago" + contadorPersonajes, arma, escudo);
    }

    public Mago(String nombre, Arma arma, Escudo escudo) {
        super(nombre, Mago.MOD_ATQ_INICIAL, Mago.MOD_DEF_INICIAL, Mago.NUMERO_ITEMS_DEFAULT, arma, escudo);
        this.probablidadCritico = Mago.PROB_CRITICO_INICIAL;
        this.aniadaPociones();
    }


    @Override
    protected Double calcularDanio() {
        // Obtenemos el daño base del arma
        Double danioTotal = super.armaPersonaje.getDanio() * (1 + (super.ataque / 100));

        if (this.momentoAtaqueCritico()) {
            System.out.println("Ataque crítico! Un " + Mago.ATAQUE_CRITICO + "% de daño adicional"); 
            danioTotal += danioTotal * (Mago.ATAQUE_CRITICO / 100);
        }
        return danioTotal;
    }

    // Calcula si el ataque es crítico
    protected boolean momentoAtaqueCritico() {
        boolean critico = false;
        Double probabilidad = Math.random() * 100; // Lanza un número aleatorio entre 0 y 100
        if (probabilidad <= this.probablidadCritico) {
            critico = true;
        }
        return critico;
    }
    
    // Añade las pociones iniciales
    private void aniadaPociones() {
        super.items.add(new PocionDeAtaque(PocionDeAtaque.VALOR_EFECTO));
        super.items.add(new PocionDeDefensa(PocionDeDefensa.VALOR_EFECTO));
    }

    public Double getProbablidadCritico() {
        return probablidadCritico;
    }

    public void setProbablidadCritico(Double probablidadCritico) {
        this.probablidadCritico = probablidadCritico;
    }

    

}