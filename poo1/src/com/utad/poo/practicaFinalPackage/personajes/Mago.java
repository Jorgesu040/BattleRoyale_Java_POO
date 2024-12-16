/**
 * Clase Mago:
 * - Implementa la lógica de la mecánica de crítico de un personaje de tipo Mago.
 *
 * Nota:
 * - La clase Mago empieza con 2 pociones: una de defensa y otra de ataque.
 *
 * Equipamiento:
 * - Armas de la categoría ArmaMago: BastonDeSabiduria, OrbeAncestral, VaritaDeCristal
 * - Escudos normales: EscudoLigero, EscudoNormal, EscudoPesado
 *
 * Características:
 * - Hechizo de defensor mágico que aumenta su defensa base (defensa +5.0%)
 * - Magia rúnica que aumenta su daño base (ataque +5.0%)
 * - Un bastón mágico que le permite lanzar hechizos mágicos y tiene una pequeña probabilidad (2%) de hacer un ataque crítico (+50%)
 *   - La probabilidad de ataque crítico aumenta en +2 hasta un 10%.
 */
package com.utad.poo.practicaFinalPackage.personajes;

import com.utad.poo.practicaFinalPackage.items.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.utad.poo.practicaFinalPackage.herramientas.*;

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
        this("Mago " + contadorPersonajes, arma, escudo);
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

    @Override
    public BufferedImage seleccionarImagen() {
        BufferedImage imagen = null;

        String filePrefix = estaVivo() ? "mago" : "derrotado";
        String fileExtension = ".png";

        try {
            // Intentar cargar la imagen desde el .jar usando InputStream
            InputStream inputStream = getClass().getResourceAsStream("/" + filePrefix + fileExtension);
            if (inputStream != null) {
                imagen = ImageIO.read(inputStream);
            } else {
                System.err.println("Archivo no encontrado en el .jar: " + filePrefix + fileExtension);
            }
        } catch (IOException ex) {
            System.err.println("Error al cargar la imagen desde el .jar: " + filePrefix + fileExtension);
            ex.printStackTrace();
        }

        return imagen;
    }

    public Double getProbablidadCritico() {
        return probablidadCritico;
    }

    public void setProbablidadCritico(Double probablidadCritico) {
        this.probablidadCritico = probablidadCritico;
    }

    @Override
    public String getSpecialAbility() {
        return "Probabilidad de crítico: " + probablidadCritico + "%";
    }

}