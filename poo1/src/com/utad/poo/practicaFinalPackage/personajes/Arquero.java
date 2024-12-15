/**
 * Clase Arquero:
 * - Implementa la mecánica de puntería de un personaje de tipo arquero.
 *
 * Nota:
 * - Mecánica de puntería:
 *   - Cuanto mayor sea la puntería, menor probabilidad tiene el enemigo de huir.
 *   - Esta mecánica aplica un porcentaje reductor de probabilidad de huida del enemigo.
 *
 * Equipamiento:
 * - Armas de la categoría ArmaArquero: ArcoDeGuerrila, ArcoDePrecision, Ballesta
 * - Escudos normales: EscudoLigero, EscudoNormal, EscudoPesado
 *
 * Características:
 * - Agilidad del arquero (defensa +10.0%)
 * - Sin bonus de ataque inicial (ataque +0.0%)
 * - Con una puntería inicial de 10, que puede aumentar hasta 20.
 */
package com.utad.poo.practicaFinalPackage.personajes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.utad.poo.practicaFinalPackage.herramientas.Arma;
import com.utad.poo.practicaFinalPackage.herramientas.Escudo;

public class Arquero extends Personaje {

    public static final Double PUNTERIA_INICIAL = 10.0;
    public static final Double PUNTERIA_INCREMENTO = 2.0;
    public static final Double PUNTERIA_MAX = 20.0;
    public static final Double MOD_DEF_INICIAL = 10.0;


    private Double punteria;

    public Arquero(Arma arco, Escudo escudo) {
        this("Arquero " + contadorPersonajes, arco, escudo);
        
    }

    public Arquero(String nombre, Arma arco, Escudo escudo) {
        super(nombre, Personaje.ATAQUE_DEFAULT, Arquero.MOD_DEF_INICIAL, Personaje.NUMERO_ITEMS_DEFAULT, arco, escudo);
        this.punteria = Arquero.PUNTERIA_INICIAL;    
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
    
        String filePrefix = estaVivo() ? "arquero" : "derrotado";
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


    public Double getPunteria() {
        return punteria;
    }

    public void setPunteria(Double punteria) {
        this.punteria = punteria;
    }

    @Override
    public String getSpecialAbility() {
        return "Puntería: " + punteria;
    }

    

}