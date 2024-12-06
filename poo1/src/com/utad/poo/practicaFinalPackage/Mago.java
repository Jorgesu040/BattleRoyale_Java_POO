package com.utad.poo.practicaFinalPackage;

/* Clase mago:
    - Implementa la lógica de ataque, defensa, contraataque y retirada de un personaje de tipo mago.
    - Hereda de la clase Personaje.
    - Implementa los métodos abstractos de la clase Personaje.

    Especialidad: 
    - Herramienta de ataque: hechizos mágicos.
    - Herramienta de defensa: escudo mágico.
    
    Nota:
    - La clase Mago empieza con 2 objetos de tipo Item de tipo aleatorio.
    - Tiene mas probabilidad de encontrar objetos de tipo Item.
    - Tiene mas probabilidad de evitar las trampas.

    Características / Equipamiento:
    - Armadura del mago (defensa +5.0%)
    - Poder runico que le permite aumentar el daño de su ataque (+5.0%)
    - Un bastón mágico que le permite lanzar hechizos mágicos y tiene una pequeña probabilidad (1%) hacer un ataque crítico (+50%)
        - La probabilidad de ataque crítico aumenta en +2 hasta un 10%.
*/


public class Mago extends Personaje {

    public static final Double ATAQUE_CRITICO = 50.0;

    public static final Double PROB_CRITICO_INICIAL = 1.0;
    public static final Double PROB_CRITICO_INCREMENTO = 2.0;
    public static final Double PROB_CRITICO_MAX = 10.0;

    public static final Double MOD_ATQ_INICIAL = 5.0;
    public static final Double MOD_DEF_INICIAL = 5.0;

    private Double probablidadCritico;
    private Arma arma;


    public Mago() {
        this("Mago" + contadorPersonajes);
    }

    public Mago(String nombre) {
        super(nombre, Mago.MOD_ATQ_INICIAL, Mago.MOD_DEF_INICIAL, Personaje.NUMERO_ITEMS_DEFAULT);
        probCritico = Mago.PROB_CRITICO_INICIAL;
    }

    public void equiparArma(Arma arma) {
        this.arma = arma;
    }

    // Implement abstract methods
    @Override
    public void atacar(Personaje personaje) {
        // Mago attack logic with special abilities
        Double danioTotal = calcularDanio();
        personaje.recibirDanio(danioTotal);
    }

    private Double calcularDanio() {
        Double danioBase = this.ataque + arma.getDanio();
        // Aplicar modificadores del arma
        // ...existing code...
        return danioBase;
    }

    @Override
    public void defensa(Personaje personaje) {
        // Mago defense logic
    }

    @Override
    public void contraataque(Personaje personaje, Double ataque) {
        // Mago counterattack logic
    }

    @Override
    public void retirada(Personaje personaje) {
        // Mago retreat logic
    }
}