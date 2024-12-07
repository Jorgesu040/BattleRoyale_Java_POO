package com.utad.poo.practicaFinalPackage.personajes;

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

    public Mago() {
        this("Mago" + contadorPersonajes);
    }

    public Mago(String nombre) {
        super(nombre, Mago.MOD_ATQ_INICIAL, Mago.MOD_DEF_INICIAL, Personaje.NUMERO_ITEMS_DEFAULT);
        this.probablidadCritico = Mago.PROB_CRITICO_INICIAL;
    }

     // Metodo que calcula el daño total del guerrero y se lo pasa al padre para que lo aplique
     public void atacar(Personaje personaje) {
        Double danioTotal = this.calcularDanio();
        super.atacar(personaje, danioTotal);
    }

    // El hijo calcula el daño total
    private Double calcularDanio() {
        // Obtenemos el daño base del arma
        Double danioTotal = super.armaPerosonaje.getDanio();        
        // Le pedimos al padre que nos calcule el daño total teniendo en cuenta el ataque del personaje
        danioTotal += super.calcularDanio(danioTotal);

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

    @Override
    public void defensa(Personaje personaje) {
        // Mago defense logic
    }
        // Mago defense logic
    

    @Override
    public void contraataque(Personaje personaje, Double ataque) {
        // Mago counterattack logic
    }

    @Override
    public void retirada(Personaje personaje) {
        // Mago retreat logic
    }
}