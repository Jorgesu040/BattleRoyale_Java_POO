package com.utad.poo.practicaFinalPackage.personajes;

import com.utad.poo.practicaFinalPackage.herramientas.*;

/* Clase arquero:
    - Implementa la mecánica de puntería de un personaje de tipo arquero.

    Nota:
    - Mecanica de punteria: 
        - Cuanto mayor sea el punteria, menor probabilidad tiene el enemigo de huir.
        - Esta mecanica aplica un porcentaje reductor de probabilidad de huida del enemigo.
    
    Equipamiento:
        - Armas de la catergoria ArmaArquero: ArcoDeGuerrila, ArcoDePrecision, Ballesta
        - Escudos normales: EscudoLigero, EscudoNormal, EscudoPesado

    Características:
    - Agilidad del arquero(defensa +10.0%)
    - Sin bonus de ataque inicial. (ataque +0.0%)
    - Con una punteria inicial de 10, que puede aumentar hasta 20.
*/


public class Arquero extends Personaje {

    public static final Double PUNTERIA_INICIAL = 10.0;
    public static final Double PUNTERIA_INCREMENTO = 2.0;
    public static final Double PUNTERIA_MAX = 20.0;
    public static final Double MOD_DEF_INICIAL = 10.0;


    private Double punteria;

    public Arquero(Arma arco, Escudo escudo) {
        this("Arquero" + contadorPersonajes, arco, escudo);
        
    }

    public Arquero(String nombre, Arma arco, Escudo escudo) {
        super(nombre, Personaje.ATAQUE_DEFAULT, Arquero.MOD_DEF_INICIAL, Personaje.NUMERO_ITEMS_DEFAULT, arco, escudo);
        this.punteria = Arquero.PUNTERIA_INICIAL;    
    }

    public Double getPunteria() {
        return punteria;
    }

    public void setPunteria(Double punteria) {
        this.punteria = punteria;
    }

}