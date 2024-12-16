package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Utility 
{

	  private static List<Integer> generatedSpecialTiles = new ArrayList<Integer>();
	  private static List<Integer> generatedSkins = new ArrayList<Integer>();
	
	public Utility()
	{

	}

	public BufferedImage getImage(String name) {
        BufferedImage imagen = null;
        String basePath = "";

        try {
            File currentDir = new File(System.getProperty("user.dir"));
            basePath = currentDir.getCanonicalPath() + "\\poo1\\files\\";
        } catch (IOException e) {
            System.err.println("Error al obtener el directorio de las imágenes.");
            e.printStackTrace();
        }

        String nombreArchivo = basePath + name;

        try {
            File archivoImagen = new File(nombreArchivo);
            imagen = ImageIO.read(archivoImagen);
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + nombreArchivo);
            e.printStackTrace();
        }

        return imagen;
    }
	
	public Integer generateRandom(Integer min, Integer max)
    {
    	Random r = new Random();
    	Integer randomNum;

        do {
            randomNum = r.nextInt(max - min) + min; 
        } while (Utility.generatedSpecialTiles.contains(randomNum)); 

        Utility.generatedSpecialTiles.add(randomNum); 
        
        return randomNum;
    }
	
	public Integer generateRandomSkin(Integer min, Integer max)
    {
    	Random r = new Random();
    	Integer randomNum;

        do {
            randomNum = r.nextInt(max - min) + min; 
        } while (Utility.generatedSkins.contains(randomNum)); 

        Utility.generatedSkins.add(randomNum); 
        
        return randomNum;
    }

	
	 public TileType generateRandomTileType() 
	    {
	        TileType[] values = TileType.values();
	        TileType result = null;
	        
	   
	        Integer randomIndex = this.generateSimpleRandom(values.length);
	        
	        if (randomIndex < (values.length / 4))
	        {
	        	result = TileType.TILE_OBSTACLE;
	        }
	        else
	        {
	        	result = TileType.TILE_FREE_SPACE;
	        }
	        
	      
	        
	        return result;
	    }
	    
	 	public Integer generateSimpleRandom(Integer lenght)
	    {
	    	return (int) (Math.random() * lenght);
	    }
	    
	
}
