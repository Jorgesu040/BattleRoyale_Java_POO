package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Utility 
{

	  private static List<Integer> generatedSpecialTiles = new ArrayList<Integer>();
	  private static List<Integer> generatedSkins = new ArrayList<Integer>();
	
	public Utility()
	{

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
