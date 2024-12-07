package com.utad.poo.practicaFinalPackage.personajes;

/* Clase guerrero:
    - Implementa la lógica de ataque, defensa, contraataque y retirada de un personaje de tipo Guerrero.

    Especialidad: 
    - Herramienta de ataque: espada del guerrero.
    - Herramienta de defensa: escudo del guerrero.   

    Nota:
    - El guerrero tiene un ataque mayor.
    - El guerrero tiene una defensa mayor.
    - El guerrero tiene una probabilidad de contraataque alta 
    - El guerrero tiene una probabilidad de retirada baja.

    Características / Equipamiento:
    - Armadura del guerrero (defensa +20.0%)
    - Una gran fuerza que le permite aumentar la fuerza de su ataque (+20.0%)
    - Una característica especial que le permite contraatacar con mayor probabilidad, llamada 'Ira Espartana'
        - Este atributo se inicializa a 0 y aumenta en 5% cada vez que el guerrero recibe un ataque. (Max 30%)
*/

public class Guerrero extends Personaje {
    public static final Double IRA_ESPARTANA_INICIAL = 0.0;
    public static final Double IRA_ESPARTANA_INCREMENTO = 5.0;
    public static final Double IRA_ESPARTANA_MAX = 30.0;

    public static final Double MOD_ATQ_INICIAL = 20.0;
    public static final Double MOD_DEF_INICIAL = 20.0;

    private Double iraEspartanaContraataque;

    public Guerrero() {
        this("Guerrero" + contadorPersonajes);
    }

    public Guerrero(String nombre) {
        super(nombre, Guerrero.MOD_ATQ_INICIAL, Guerrero.MOD_DEF_INICIAL, Personaje.NUMERO_ITEMS_DEFAULT);
        this.iraEspartanaContraataque = Guerrero.IRA_ESPARTANA_INICIAL;
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

        return danioTotal;
    }

    @Override
    public void defensa(Personaje personaje) {
        // Guerrero defense logic
    }

    @Override
    public void contraataque(Personaje personaje, Double ataque) {
        // Guerrero counterattack logic
    }

    @Override
    public void retirada(Personaje personaje) {
        
    }
}