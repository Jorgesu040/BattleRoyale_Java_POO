/**
 * Clase Personaje:
 * - Clase abstracta que define los atributos y métodos comunes a todos los personajes.
 *
 * Atributos de vida, ataque, defensa:
 * - Vida: cantidad de vida del personaje.
 * - Ataque: Modificador porcentual que aumenta el daño que inflige el personaje.
 * - Defensa: Modificador porcentual que reduce el daño que recibe el personaje.
 *
 * Atributos de inventario:
 * - Items: lista de objetos que el personaje puede llevar consigo.
 * - NumeroItems: número máximo de objetos que el personaje puede llevar consigo.
 *
 * Atributos de estado:
 * - Estado: estado actual del personaje (atacando, defendiendo, retirándose, etc.)
 *
 * Atributos de efectos:
 * - List<Item> efectos: lista de objetos que modifican los atributos del personaje.
 * - En el método reiniciarModificadores se recorren los efectos y se revierten los cambios.
 *
 * Atributos de herramientas:
 * - Arma: arma equipada por el personaje.
 * - Escudo: escudo equipado por el personaje.
 *
 * Métodos de ataque:
 * - Atacar: método que lleva a cabo
 * - CalcularDanio: método que calcula el daño total que inflige el personaje.
 *
 * Métodos de defensa:
 * - Defensa: método que reduce el daño recibido por el personaje.
 * - Contraataque: método que devuelve un porcentaje del daño recibido al oponente.
 * - ContraAtaco: método que devuelve si el personaje ha contraatacado o no.
 *
 * Métodos de retirada:
 * - Retirada: método que permite al personaje retirarse de la batalla, evitando recibir daño.
 *
 * Métodos de modificación de atributos:
 * - IncrementarAtaque/Defensa/ProbabilidadRetirada/Vida: métodos que aumentan los atributos del personaje.
 * - DecrementarAtaque/Defensa/ProbabilidadRetirada/Vida: métodos que disminuyen los atributos del personaje.
 *   - Usados para aplicar los efectos de los items o revertirlos.
 *   - Usados para quitar/añadir vida al personaje.
 *
 * Métodos misceláneos:
 * - RecibirAtaque: método que reduce la vida del personaje tras recibir un ataque.
 * - EstaVivo: método que devuelve si el personaje está vivo o no.
 * - ReiniciarModificadores: método que reinicia los modificadores de los items del personaje.
 *
 * Métodos de lootear enemigo:
 * - lootearEnemigo: método que permite al personaje quedarse con los Items del enemigo tras derrotarlo.
 * - CambiarEscudo: método que permite al personaje cambiar su escudo por el del enemigo.
 */
package com.utad.poo.practicaFinalPackage.personajes;

import java.util.ArrayList;
import java.util.List;

import com.utad.poo.practicaFinalPackage.herramientas.Arma;
import com.utad.poo.practicaFinalPackage.herramientas.Escudo;
import com.utad.poo.practicaFinalPackage.inout.CreateLogs;
import com.utad.poo.practicaFinalPackage.interfazGrafica.Tile;
import com.utad.poo.practicaFinalPackage.items.*;
import java.awt.image.BufferedImage;


public abstract class Personaje {

    // Atributos default del personaje
    public static final Integer VIDA_DEFAULT = 100; // Puntos de vida
    public static final Double ATAQUE_DEFAULT = 0.0; // %
    public static final Double DEFENSA_DEFAULT = 0.0; // %
    public static final Double PROBABILIDAD_RETIRADA_DEFAULT = 20.0; // %
    public static final Double PROBABILIDAD_CONTRAATAQUE_DEFAULT = 10.0; // %
    public static final Double DANIO_CONTRAATAQUE_DEFAULT = 50.0; // %

    public static final Integer NUMERO_ITEMS_DEFAULT = 0;
    public static final Integer NUMERO_ITEMS_MAX = 5;

    // Contador de personajes (usado para asignar un id único a cada personaje)
    public static Integer contadorPersonajes = 0;

    // Atributos del personaje
    protected Integer id;

    protected String nombre;
    protected Integer vida;
    protected Double ataque;
    protected Double defensa;
    protected Double probabilidadRetirada;
    protected EstadoPersonaje estado;
    protected Boolean retiradaConExito;

    protected Arma armaPersonaje;
    protected Escudo escudoPersonaje;

    protected List<Item> items; // Pociones, vendas, botiquines, etc.
    protected List<Item> efectos; // Efectos negativos de las trampas, etc.
    protected Integer numeroItems;

    protected BufferedImage imagen;
    protected Tile ubicacion_personaje;
    protected Tile targetTile;

    protected Boolean isAI;


    /* Constructor (Solo uno, los hijos se encargan de los Defaults) */
    public Personaje(String nombre, Double ataque, Double defensa, Integer numeroItems, Arma arma, Escudo escudo) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.numeroItems = numeroItems;
        this.armaPersonaje = arma;
        this.escudoPersonaje = escudo;

        this.probabilidadRetirada = Personaje.PROBABILIDAD_RETIRADA_DEFAULT;
        this.estado = EstadoPersonaje.NADA;
        this.vida = Personaje.VIDA_DEFAULT;
        this.items = new ArrayList<Item>();
        this.efectos = new ArrayList<Item>();
        this.id = ++contadorPersonajes;
        this.imagen = seleccionarImagen();

        // TODO: Añadir su tile aqui o luego tras la instanciación?
        this.ubicacion_personaje = null;
        this.targetTile = null;

        this.isAI = false;
    }

    public abstract BufferedImage seleccionarImagen();

    /* Métodos Opciones de combate */

    /// ************* Logica de ataque ************* ///
    public void atacar(Personaje opponent) {
        Double danioTotal = this.calcularDanio();
        if (danioTotal == 0.0) {
            System.out.println("¡Oh no! El personaje ha fallado el ataque");
        } else {
            System.out.println("¡El personaje ha atacado con un daño total de " + danioTotal + "!");
            opponent.recibirAtaque(danioTotal);
        }
    }

    // Este metodo del padre calcula el daño total al oponente aplicando sus
    // atributos de ataque y defensa
    protected Double calcularDanio() {
        // Obtenemos el daño base del arma
        Double danioTotal = 0.0;
        try {
            danioTotal = this.armaPersonaje.getDanio() * (1 + (this.ataque / 100));
            if (this.armaPersonaje.getPrecision() < Math.random() * 100) {
                danioTotal = 0.0; // No se ha acertado el golpe
            }
        } catch (Exception e) {
            System.out.println("Error al calcular el daño: " + e.getMessage());
        }
        return danioTotal;
    }

    // ************* Logica de defensa ************* ///
    // Defiende de un ataque a un personaje -> reduce el y pequeña posibilidad de
    // contraataque (devolver un porcentaje del daño recibido)
    public void defensa(Personaje opponent) {
        if (contraAtaco() && opponent.getEstado() == EstadoPersonaje.ATACANDO) {
            this.contraataque(opponent, opponent.ataque);
        }
    }

    // Sub-mecanica de la defensa: contraataque (devolver un la mitad del daño
    // recibido)
    protected void contraataque(Personaje opponent, Double ataque) {
        opponent.recibirAtaque(ataque * Personaje.DANIO_CONTRAATAQUE_DEFAULT / 100);
        System.out.println("¡Que suerte! El personaje ha contraatacado y ha devuelto un "
                + Personaje.DANIO_CONTRAATAQUE_DEFAULT + "% del daño recibido!");
    }

    protected Boolean contraAtaco() {
        Boolean contraAtaco = false;
        Double probabilidadContraataque = Personaje.PROBABILIDAD_CONTRAATAQUE_DEFAULT;
        if (probabilidadContraataque > Math.random() * 100) {
            contraAtaco = true;
        }
        return contraAtaco;
    }

    // ************* Logica de retirada ************* ///
    // Se retira de la batalla (probabilidad pequeña) -> no recibe daño
    public void retirada(Personaje opponent) {
        Boolean retirada = false;
        Double probabilidadRetirada = Personaje.PROBABILIDAD_RETIRADA_DEFAULT;

        // Comprobar si el personaje oponente es un arquero y reducir la probabilidad de
        // retirada
        if (opponent != null && opponent instanceof Arquero) {
            probabilidadRetirada = Math.max(probabilidadRetirada - ((Arquero) opponent).getPunteria(), 0);
            System.out.println("El oponente es un arquero muy preciso y redujo tu probabilidad de retirada en "
                    + ((Arquero) opponent).getPunteria() + "%");
        }

        // Comprobar si el personaje tiene un escudo y aumentar/disminuir la
        // probabilidad de retirada
        System.out.println("Las características del escudo dan un modificador de "
                + this.escudoPersonaje.getProbabilidadEscape() + "% a la probabilidad de retirada");
        probabilidadRetirada = Math.max(probabilidadRetirada + this.escudoPersonaje.getProbabilidadEscape(), 0);

        // Calcular la probabilidad de retirada
        if (Math.random() * 100 <= probabilidadRetirada) {
            retirada = true;
        }

        this.retiradaConExito = retirada;
        
    }

    // ************* Logica de recibir ataque ************* ///
    // Recibe un ataque -> reduce la vida del personaje, tiene en cuenta la defensa
    // del personaje
    // El ataque es un valor precalculado por el oponente que se le pasa al
    // personaje (ya incluye posibles modificadores: críticos, bonus de ataque,
    // etc.)
    public void recibirAtaque(Double ataque) {
        if (estado == EstadoPersonaje.DEFENDIENDO) {
            ataque = ataque * (1 - this.escudoPersonaje.getDefensa()/100.0); // Reducir el daño recibido
            CreateLogs.addLog(this.nombre + " se ha defendido y ha reducido el daño recibido");
        }

        if (estado == EstadoPersonaje.RETIRANDOSE && this.retiradaConExito) {
            ataque = 0.0; // No recibe daño
            CreateLogs.addLog(this.nombre + " ha evitado el ataque con éxito");}

        this.vida -= (int) (ataque - (ataque * this.defensa/100.0));

        CreateLogs.addLog(this.nombre + " ha recibido un ataque de " + ataque.intValue() + " puntos pero gracias a su defensa ha recibido " + (int) (ataque - (ataque * this.defensa/100.0)) + " de daño"); 

        if (this.vida < 0) {
            this.vida = 0;
            // TODO: Decir quien ha derrotado al personaje
            CreateLogs.addLog(this.nombre + " ha sido derrotado en combate");
            
        }

    }

    /* Métodos de lootear enemigo */
    // TODO: yo sinceramente no lo implementaría
    // Permite al personaje obtener los objetos del enemigo derrotado
    public void lootearEnemigo(Personaje oponente) {
        // ** Código agregado por el asistente **
        if (!oponente.estaVivo()) {
            System.out.println(this.nombre + " ha derrotado a " + oponente.nombre + " y lootea sus objetos");

            // TODO: Menu con los items del enemigo
            
            // TODO: Dar opcion de cambiar de escudo
        }
    }

    /* Métodos de incremento/decremento de atributos y estado */
    public void incrementarAtaque(Double valorEfecto) {
        this.ataque += valorEfecto;
        System.out.println(this.nombre + " ha incrementado su ataque en " + valorEfecto);
    }

    public void incrementarDefensa(Double valorEfecto) {
        this.defensa += valorEfecto;
        if (this.defensa > 100.0) {
            this.defensa = 100.0; // La defensa no puede superar el 100%
        }
        System.out.println(this.nombre + " ha incrementado su defensa en " + valorEfecto);
    }

    public void incrementarProbabilidadRetirada(Double valorEfecto) {
        this.probabilidadRetirada += valorEfecto;
        System.out.println(this.nombre + " ha incrementado su probabilidad de retirada en " + valorEfecto);
    }

    public void incrementarVida(Integer valorEfecto) {
        if (this.vida + valorEfecto > Personaje.VIDA_DEFAULT) {
            this.vida = Personaje.VIDA_DEFAULT;
        } else {
            this.vida += valorEfecto;
        }
        System.out.println(this.nombre + " ha recuperado " + valorEfecto + " puntos de vida");
    }

    public void decrementarAtaque(Double valor) {
        this.ataque -= valor;
        if (this.ataque < 0) {
            this.ataque = 0.0;
        }
    }

    public void decrementarDefensa(Double valor) {
        this.defensa -= valor;
        if (this.defensa < 0) {
            this.defensa = 0.0;
        }
    }

    public void decrementarProbabilidadRetirada(Double valor) {
        this.probabilidadRetirada -= valor;
        if (this.probabilidadRetirada < 0) {
            this.probabilidadRetirada = 0.0;
        }
    }

    public void decrementarVida(Integer valor) {
        this.vida -= valor;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public Boolean estaVivo() {
        return this.vida > 0;
    }

    // Método que reinicia los modificadores de los items del personaje
    public void reiniciarModificadores() {
        if (!this.efectos.isEmpty()) {
            List<Item> itemsToRemove = new ArrayList<>();
            for (Item item : this.efectos) {
                item.revertir(this);
                itemsToRemove.add(item);
            }
            this.efectos.removeAll(itemsToRemove);
        }

        if (!this.items.isEmpty()) {
            List<Item> itemsToRemove = new ArrayList<>();
            for (Item item : this.items) {
                if (item.isHaSidoUsada()){
                    item.revertir(this);
                    itemsToRemove.add(item);
                }
            }
            this.items.removeAll(itemsToRemove);
        }
    }

    /* Métodos de inventario */
    public void addItem(Item item) {
        if (this.items.size() < this.numeroItems) {
            this.items.add(item);
            System.out.println(this.nombre + " ha obtenido " + item.getNombre());
        } else {
            System.out.println("No se pueden añadir más items al inventario de " + this.nombre);
        }
    }

    public void addEfecto(Item item) {
        this.efectos.add(item);
        System.out.println(this.nombre + " ha sido afectado por " + item.getNombre());
    }
    
    // Metodo que reinicie el estado del personaje al final de un turno
    public void reiniciarEstado() {
        this.estado = EstadoPersonaje.NADA;
        this.reiniciarModificadores();
    }
    
    public void updateImagen() {
        this.imagen = seleccionarImagen();
    }

    /* Getters y Setters */

    public String getNombre() {
        return nombre;
    }

    public Integer getVida() {
        return vida;
    }

    public Double getAtaque() {
        return ataque;
    }

    public Double getDefensa() {
        return defensa;
    }

    public Double getProbabilidadRetirada() {
        return probabilidadRetirada;
    }

    public EstadoPersonaje getEstado() {
        return estado;
    }

    public Arma getArmaPersonaje() {
        return armaPersonaje;
    }

    public Escudo getEscudoPersonaje() {
        return escudoPersonaje;
    }

    public void setEstado(EstadoPersonaje estado) {
        this.estado = estado;
    }

    public BufferedImage getImagen() {
        return this.imagen;
    }

    public Tile getUbicacionPersonaje() {
        return ubicacion_personaje;
    }

    public void setUbicacionPersonaje(Tile ubicacion_personaje) {
        this.ubicacion_personaje = ubicacion_personaje;
    }

    public Tile getTargetTile() {
        return targetTile;
    }

    public void setTargetTile(Tile targetTile) {
        this.targetTile = targetTile;
    }
    
    public List<Item> getItems() {
        return items;
    }

    public List<Item> getEfectos() {
        return efectos;
    }

    public void setEfectos(List<Item> efectos) {
        this.efectos = efectos;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getNumeroItems() {
        return numeroItems;
    }
    
    public Arma getArma() {
        return this.armaPersonaje;
    }

    public Escudo getEscudo() {
        return this.escudoPersonaje;
    }

    public Boolean getIsAI() {
        return isAI;
    }

    public void setIsAI(Boolean isAI) {
        this.isAI = isAI;
    }
    
    public abstract String getSpecialAbility();

    @Override
    public String toString() {
        return "Personaje [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", ataque=" + ataque + ", defensa="
                + defensa + ", probabilidadRetirada=" + probabilidadRetirada + ", estado=" + estado
                + ", retiradaConExito=" + retiradaConExito + ", armaPersonaje=" + armaPersonaje + ", escudoPersonaje="
                + escudoPersonaje + ", items=" + items + ", efectos=" + efectos + ", numeroItems=" + numeroItems
                + ", imagen=" + imagen + ",\ntargetTile=" + (targetTile != null ? targetTile : "ninguno")
                + "]";
    }


    
    

}