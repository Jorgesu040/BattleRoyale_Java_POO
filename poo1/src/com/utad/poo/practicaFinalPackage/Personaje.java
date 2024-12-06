package com.utad.poo.practicaFinalPackage;

import java.util.List;


/* Clase Personaje:
    - Clase abstracta que define los atributos y métodos comunes a todos los personajes.

    Atributos de vida, ataque, defensa:
    - Vida: cantidad de vida del personaje.
    - Ataque: Modificador porcentual que aumenta el daño que inflige el personaje.
    - Defensa: Modificador porcentual que reduce el daño que recibe el personaje.

    Nota:


    Métodos misceláneos:
    - RecibirAtaque: método que reduce la vida del personaje tras recibir un ataque.
    - EstaVivo: método que devuelve si el personaje está vivo o no.
    
    Métodos de lootear enemigo:
    - lootearEnemigo: método que permite al personaje quedarse con los Items del enemigo tras derrotarlo.
    - CambiarEscudo: método que permite al personaje cambiar su escudo por el del enemigo.



*/

public abstract class Personaje {
    
    // Atributos default del personaje
    protected static final Integer VIDA_DEFAULT = 100;
    protected static final Double ATAQUE_DEFAULT = 0.0;
    protected static final Double DEFENSA_DEFAULT = 0.0;
    protected static final Integer NUMERO_ITEMS_DEFAULT = 0;
    protected static final Integer NUMERO_ITEMS_MAX = 5;

    // Contador de personajes (usado para asignar un id único a cada personaje)
    protected static Integer contadorPersonajes = 0; 

    // Atributos del personaje 
    protected Integer id;

    protected String nombre;
    protected Integer vida;
    protected Double ataque;
    protected Double defensa;
    
    protected List<Item> items;
    protected Integer numeroItems;  

    /* Constructor (Solo uno, los hijos se encargan de los Defaults) */
    public Personaje(String nombre, Double ataque, Double defensa, Integer numeroItems) {
        this.id = ++contadorPersonajes;
        this.nombre = nombre;
        this.vida = Personaje.VIDA_DEFAULT;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    /* Métodos */
    
    // Ataca a un personaje
    public abstract void atacar(Personaje personaje);
    // Defiende de un ataque a un personaje -> reduce el daño recibido y pequeña posibilidad de contraataque (devolver un porcentaje del daño recibido)
    public abstract void defensa(Personaje personaje);
    public abstract void contraataque(Personaje personaje, Double ataque);

    // Se retira de la batalla (probabilidad pequeña, en cualquier caso el personaje recibe el daño del oponente, pero si se retira, tiene un turno para usar objetos)
    public abstract void retirada(Personaje personaje);
    
    // Recibe un ataque -> reduce la vida del personaje, tiene en cuenta la defensa del personaje
    public void recibirAtaque(Double ataque) {
        this.vida -= (int)(ataque - (ataque * this.defensa));
    }
    
    public Boolean estaVivo() {
        return this.vida > 0;
    }

    //Metodos lootear enemigo: si tras un enfrentamiento el personaje lo elimina, puede quedarse con los objetos del enemigo y cambiar su escudo
    public void lootearEnemigo(Personaje personaje) {
        // Comprobar si el enemigo ha sido derrotado
        // Preguntar por cambio de escudo 
        // Quitar objetos al enemigo 
        // Añadir objetos al personaje
    }



    /* Getters y Setters */


    /* Misceláneos */
    @Override
    public String toString() {
        return "Personaje [id=" + id + ", ataque=" + ataque + ", defensa=" + defensa + ", nombre=" + nombre + ", vida=" + vida + "]";
    }
}
