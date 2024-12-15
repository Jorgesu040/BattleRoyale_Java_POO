/**
 * Clase Guerrero:
 * - Implementa los métodos de la mecánica de contraataque de un personaje de tipo Guerrero.
 * - (Polimorfismo) Sobreescribe el método recibirAtaque para implementar la mecánica de aumento de la ira espartana.
 *
 * Nota:
 * - Mecánica de contraataque:
 *   - El guerrero aumenta su probabilidad de contraataque cada vez que recibe un ataque.
 *   - La probabilidad de contraataque se inicializa a 0 y aumenta en 5% cada vez que el guerrero recibe un ataque. (Máximo 30%)
 *
 * Equipamiento:
 * - Armas de la categoría ArmaGuerrero: EspadaBastarda, HachaDobleFilo, LanzaPuntiaguda
 * - Escudos normales: EscudoLigero, EscudoNormal, EscudoPesado
 *
 * Características:
 * - Fortaleza del guerrero (defensa +20.0%)
 * - Una gran fuerza que le permite aumentar la fuerza de su ataque (+20.0%)
 * - Una característica especial que le permite contraatacar con mayor probabilidad, llamada 'Ira Espartana'
 *   - Este atributo se inicializa a 0 y aumenta en 5% cada vez que el guerrero recibe un ataque. (Máximo 30%)
 */
package com.utad.poo.practicaFinalPackage.personajes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.utad.poo.practicaFinalPackage.herramientas.*;


public class Guerrero extends Personaje {
    public static final Double IRA_ESPARTANA_INICIAL = 0.0;
    public static final Double IRA_ESPARTANA_INCREMENTO = 5.0;
    public static final Double IRA_ESPARTANA_MAX = 30.0;

    public static final Double MOD_ATQ_INICIAL = 20.0;
    public static final Double MOD_DEF_INICIAL = 20.0;

    private Double iraEspartanaContraataque;

    public Guerrero(Arma arma, Escudo escudo) {
        this("Guerrero " + contadorPersonajes, arma, escudo);
    }

    public Guerrero(String nombre, Arma arma, Escudo escudo) {
        super(nombre, Guerrero.MOD_ATQ_INICIAL, Guerrero.MOD_DEF_INICIAL, Personaje.NUMERO_ITEMS_DEFAULT, arma, escudo);
        this.iraEspartanaContraataque = Guerrero.IRA_ESPARTANA_INICIAL;
    }

    @Override
    protected Boolean contraAtaco() {
        Boolean contraAtaco = false;
        Double probabilidadContraataque = Personaje.PROBABILIDAD_CONTRAATAQUE_DEFAULT + this.iraEspartanaContraataque;
        if (probabilidadContraataque > Math.random() * 100) {
            contraAtaco = true;
        }
        return contraAtaco;
    }

    @Override
    public void recibirAtaque(Double danio) {
        super.recibirAtaque(danio);
        this.iraEspartanaContraataque += Guerrero.IRA_ESPARTANA_INCREMENTO;
        this.iraEspartanaContraataque = Math.min(this.iraEspartanaContraataque, Guerrero.IRA_ESPARTANA_MAX);
        System.out.println("Tras recibir un ataque, la ira espartana del guerrero aumenta a " + this.iraEspartanaContraataque + "%");
    }

    @Override
    public BufferedImage seleccionarImagen() {
        BufferedImage imagen = null;
        String basePath = "";
    
        try {
            File currentDir = new File(System.getProperty("user.dir"));
            basePath = currentDir.getCanonicalPath() + "\\poo1\\files\\";
        } catch (IOException e) {
            System.err.println("Error al obtener el directorio de las imágenes.");
            e.printStackTrace();
        }
    
        String filePrefix = estaVivo() ? "guerrero" : "derrotado";
        String fileExtension = ".png";
        String nombreArchivo = basePath + filePrefix + fileExtension;
    
        try {
            File archivoImagen = new File(nombreArchivo);
            imagen = ImageIO.read(archivoImagen);
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + nombreArchivo);
            e.printStackTrace();
        }
    
        return imagen;
    }


    public void setIraEspartanaContraataque(Double iraEspartanaContraataque) {
        this.iraEspartanaContraataque = iraEspartanaContraataque;
    }

    public Double getIraEspartanaContraataque() {
        return iraEspartanaContraataque;
    }

    @Override
    public String getSpecialAbility() {
        return "Ira Espartana: " + iraEspartanaContraataque + "%";
    }

    
}