package com.utad.poo.practicaFinalPackage.interfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Polygon;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


/*
 * Esta clase se tiene que hacer en condiciones
 * Solo se encargara de construir en una ventana todos los tiles
 * Sera la clase tile exclusivamente quien se encargara de dibujarse
 * Por el momento esta clase es puro testing
 */

public class MapGenerator extends JPanel
{

	// Necesitado por JPanel
	private static final long serialVersionUID = 1L;
	
	public static final Integer DEFAULT_SPACING_X = 5; 
    public static final Integer DEFAULT_SPACING_Y = 5; 
    public static final Integer DEFAULT_TRAPS = 2;
    public static final Integer DEFAULT_BANDITS = 3;
    
    public static final Integer DEFAULT_SIZE = 7;
    
    private Integer centerX;
    private Integer centerY;
    
    private Integer trapsAmount;
    private Integer playerAmount;
    private Integer banditAmount;

    private List<Tile> tiles;
    private Integer size;
    
    // Variables estaticas empleadas para la generacion procedural
    private static Integer tileCounter = 0;
    private static Integer trapCounter = 0;
    private static Integer playerCounter = 0;
    private static Integer banditCounter = 0;
    


    public MapGenerator(Integer posX, Integer posY, Integer players)
    {
    	this(MapGenerator.DEFAULT_SIZE,
    			posX, posY,
    			MapGenerator.DEFAULT_TRAPS,
    			players,
    			MapGenerator.DEFAULT_BANDITS);
    }
    
    public MapGenerator(Integer size, Integer posX, Integer posY, Integer traps, Integer players, Integer bandits)
    {
    	super();
    	this.size = size;
    	this.centerX = posX;
    	this.centerY = posY;
    	this.trapsAmount = traps;
    	this.playerAmount = players;
    	this.banditAmount = bandits;
    	
    	this.tiles = new ArrayList<Tile>();
    	
    	addMouseListener(new MouseAdapter() 
    	{
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                handleTileClick(e);
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        renderGrid(g2d);
        renderMap(g2d);
        
    }
    
   
    private void renderMap(Graphics g2d)
    {
    	super.setBackground(new Color(76, 143, 220));
    }

  
    private void renderGrid(Graphics2D g2d)
    {
    	Double ang30 = Math.toRadians(30);
        Double xOff = Math.cos(ang30) * (Tile.HEXAGON_RADIOUS + MapGenerator.DEFAULT_SPACING_X);
        Double yOff = Math.sin(ang30) * (Tile.HEXAGON_RADIOUS + MapGenerator.DEFAULT_SPACING_Y);
        Integer half = this.size / 2;
    	
    	for (Integer row = 0; row < this.size; row++) 
        {
    		Integer cols = this.size - java.lang.Math.abs(row - half);
    		
            for (Integer col = 0; col < cols; col++) 
            {
                 Integer x = (int) (this.centerX + xOff * (col * 2 + 1 - cols));
                 Integer y = (int) (this.centerY + yOff * (row - half) * 3);
            	
               
                 generateRandomTile(x, y, g2d);
                
                 
            }
        }
    }
   
    
    private void generateRandomTile(Integer posX, Integer posY, Graphics2D g2d) 
    {
        Tile newTile = null;
        TileType tileType;

        /*
        if (MapGenerator.playerCounter < this.playerAmount) 
        {
            tileType = TileType.TILE_SPAWN; 
            MapGenerator.playerCounter++;
            
        } else if (MapGenerator.banditCounter < this.banditAmount) 
        {
            tileType = TileType.TILE_SPAWN_AI; 
            MapGenerator.banditCounter++;
            
        } else if (MapGenerator.trapCounter < this.trapsAmount) 
        {
            tileType = TileType.TILE_TRAP_SET; 
            MapGenerator.trapCounter++;
            
        } else 
        {
          
        }
*/
        tileType = generateRandomTileType();
        MapGenerator.tileCounter++;
        // Crear el tile con el tipo generado
        newTile = new Tile(tileType, posX, posY, false, null, MapGenerator.tileCounter);
        newTile.drawTile(g2d);
        
        this.tiles.add(newTile);
       
        
    }
    
    private TileType generateRandomTileType() 
    {
        TileType[] values = TileType.values();
        
        Integer randomIndex = (int) (Math.random() * values.length);

        	
        while (values[randomIndex] == TileType.TILE_SPAWN || 
               values[randomIndex] == TileType.TILE_SPAWN_AI || 
               values[randomIndex] == TileType.TILE_TRAP_SET ||
               values[randomIndex] == TileType.TILE_TRAP_IDLE) 
        {
            randomIndex = (int) (Math.random() * values.length);
        }
        
        return values[randomIndex];
    }
    
    private void handleTileClick(MouseEvent click) 
    {
        for (Tile tile : this.tiles) 
        {
            if (tile.contains(click.getPoint())) 
            {
                JOptionPane.showMessageDialog(
                    this,
                    tile.toString(),
                    "Información del Tile",
                    JOptionPane.INFORMATION_MESSAGE
                );
                break;
            }
        }
    }
  
}
