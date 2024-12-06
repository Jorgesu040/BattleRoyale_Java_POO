package com.utad.poo.practicaFinalPackage;

/* Clase arquero:
    - Implementa la lógica de ataque, defensa, contraataque y retirada de un personaje

    Especialidad: 
    - Herramienta de ataque: arco y flechas. 
    - Herramienta de defensa: escudo del arquero.

    Nota:
    - Mecanica de punteria: 
        - Cuanto mayor sea el punteria, menor probabilidad tiene el enemigo de contraatacar.
        - Cuanto mayor sea el punteria, menor probabilidad tiene el enemigo de huir.
        - Esta mecanica aplica un porcentaje de probabilidad de contraataque y huida del enemigo.

    Características / Equipamiento:
    - Armadura del arquero de cuero (defensa +10.0%)
    - Un arco y flechas que le permiten atacar 
    - Sin bonus de ataque inicial.
    - Con una punteria inicial de 10, que puede aumentar hasta 20.
*/


public class Arquero extends Personaje {

    public static final Double PUNTERIA_INICIAL = 10.0;
    public static final Double PUNTERIA_INCREMENTO = 2.0;
    public static final Double PUNTERIA_MAX = 20.0;
    public static final Double MOD_DEF_INICIAL = 10.0;


    private Double punteria;

    public Arquero() {
        this("Arquero" + contadorPersonajes);
        
    }

    public Arquero(String nombre) {
        super(nombre, Personaje.ATAQUE_DEFAULT, Arquero.MOD_DEF_INICIAL, Personaje.NUMERO_ITEMS_DEFAULT);
        this.punteria = Arquero.PUNTERIA_INICIAL;    
    }

    // Implement abstract methods
    @Override
    public void atacar(Personaje personaje) {
        // Arquero attack logic considering range
    }

    @Override
    public void defensa(Personaje personaje) {
        // Arquero defense logic
    }

    @Override
    public void contraataque(Personaje personaje, Double ataque) {
        // Arquero counterattack logic
    }

    @Override
    public void retirada(Personaje personaje) {
        // Arquero retreat logic
    }
}